package com.flipkart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.flipkart.dto.PaymentDto;
import com.flipkart.dto.Purchase;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.Status;
import com.flipkart.entity.Address;
import com.flipkart.entity.Customer;
import com.flipkart.entity.Transaction;
import com.flipkart.repository.CustomerRepository;
import com.flipkart.service.CheckoutService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	private CustomerRepository customerRepository;

	public CheckoutServiceImpl(@Value ("${stripe.key.secret}") String secretKey) {
		super();
		Stripe.apiKey=secretKey;
	}

	@Override
	@Transactional
	public ResponseEntity<String> placeOrder(Purchase purchase) {
		ResponseEntity<String> response= new ResponseEntity<String>();
		Customer customer = purchase.getCustomer();
		Transaction transaction = purchase.getTransaction();
		Address address = purchase.getAddress();

		if (customer != null && transaction != null && address != null && !CollectionUtils.isEmpty(purchase.getOrderItems())
				&& StringUtils.hasText(customer.getEmail())) {
			
			customer.setEmail(customer.getEmail().toLowerCase());
			Customer customerFromDb = customerRepository.findByEmail(customer.getEmail());
			if (customerFromDb != null)
				customer= customerFromDb;			
			
			transaction.setTrackingNumber(generateTrackingNumber());
			transaction.setAddress(address);

			purchase.getOrderItems().forEach(item -> transaction.add(item));

			customer.addAddress(address);
			customer.addTransaction(transaction);

			customer = customerRepository.save(customer);
			response.setStatus(Status.SUCCESS);
			response.setEntity(transaction.getTrackingNumber());
		}
		else {
			response.setMessage("SomeThing Went Wrong, Please Try again");
		}
		return response;
	}

	private String generateTrackingNumber() {
		return UUID.randomUUID().toString();
	}

	@Override
	public ResponseEntity<String> createPaymentIntent(PaymentDto paymentDto) throws StripeException{
		ResponseEntity<String> response= new ResponseEntity<String>();
		List<String> paymentMethodTypes= new ArrayList<String>();
		paymentMethodTypes.add("card");
		
		HashMap<String, Object> params= new HashMap<String, Object>();
		params.put("amount", Integer.valueOf(paymentDto.getAmount()));
		params.put("currency", paymentDto.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);
		params.put("description", "Flipkart Shopping");
		params.put("receipt_email", paymentDto.getReceiptEmail());
		
		PaymentIntent intent= PaymentIntent.create(params);
		response.setStatus(Status.SUCCESS);
		response.setEntity(intent.getClientSecret());
		
		return response;
	}

}
