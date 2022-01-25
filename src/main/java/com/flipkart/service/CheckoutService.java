package com.flipkart.service;

import com.flipkart.dto.PaymentDto;
import com.flipkart.dto.Purchase;
import com.flipkart.dto.ResponseEntity;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

	public ResponseEntity<String> placeOrder(Purchase purchase);
	
	public ResponseEntity<String> createPaymentIntent(PaymentDto paymentDto) throws StripeException;
	
}
