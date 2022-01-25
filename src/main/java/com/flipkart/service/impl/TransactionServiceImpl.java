package com.flipkart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.flipkart.dto.OrderItemDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.Status;
import com.flipkart.dto.TransactionDto;
import com.flipkart.entity.OrderItem;
import com.flipkart.entity.Transaction;
import com.flipkart.repository.TransactionRepository;
import com.flipkart.service.TransactionService;
import com.flipkart.utils.FlipkartUtils;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	private ResponseEntity<List<TransactionDto>> getResponseOfTransactionsFromPage(Page<Transaction> page) {
		ResponseEntity<List<TransactionDto>> response = new ResponseEntity<List<TransactionDto>>();
		List<TransactionDto> list = new ArrayList<TransactionDto>();
		if (page.hasContent()) {
			response.setStatus(Status.SUCCESS);
			for (Transaction transaction : page.getContent()) {
				TransactionDto dto = new TransactionDto();
				BeanUtils.copyProperties(transaction, dto, "customer", "address", "orderItems");
				list.add(dto);
			}
			response.setPageInfo(FlipkartUtils.getPageInfoFromPage(page));
		} else {
			response.setMessage("No Transactions Found ");
		}

		response.setEntity(list);
		return response;
	}

	@Transactional
	private ResponseEntity<TransactionDto> getResponseTransactionDto(Transaction transaction) {
		ResponseEntity<TransactionDto> response = new ResponseEntity<TransactionDto>();
		TransactionDto dto = new TransactionDto();
		if (transaction != null) {
			response.setStatus(Status.SUCCESS);
			BeanUtils.copyProperties(transaction, dto);
			BeanUtils.copyProperties(transaction.getCustomer(), dto.getCustomer());
			BeanUtils.copyProperties(transaction.getAddress(), dto.getAddress());
			List<OrderItemDto> itemDtos = new ArrayList<OrderItemDto>();
			for (OrderItem item : transaction.getOrderItems()) {
				OrderItemDto itemDto = new OrderItemDto();
				BeanUtils.copyProperties(item, itemDto);
				itemDtos.add(itemDto);
			}
			dto.setOrderItems(itemDtos);

			response.setEntity(dto);
		} else {
			response.setMessage("Invalid Transaction");
		}

		return response;

	}

	@Override
	public ResponseEntity<List<TransactionDto>> getTransactionsOfACustomer(String email, Pageable pageable) {

		return getResponseOfTransactionsFromPage(
				transactionRepository.findByCustomerEmailOrderByDateCreatedDesc(email.toLowerCase(), pageable));
	}

	@Override
	public ResponseEntity<TransactionDto> getTransactionDetail(String trackingNumber) {
		return getResponseTransactionDto(transactionRepository.findByTrackingNumber(trackingNumber));

	}

}
