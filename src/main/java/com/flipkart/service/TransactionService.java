package com.flipkart.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.TransactionDto;

public interface TransactionService {
	
	public ResponseEntity<List<TransactionDto>> getTransactionsOfACustomer(String email, Pageable pageable);

	public ResponseEntity<TransactionDto> getTransactionDetail(String trackingNumber);
}
