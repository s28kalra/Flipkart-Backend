package com.flipkart.entity;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import com.flipkart.repository.TransactionRepository;

@SpringBootTest
class TransactionRepositoryTest {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	public void getTransactionsByEmail() {
		Pageable pageable= Pageable.ofSize(20);
		List<Transaction> transactions = transactionRepository.findByCustomerEmailOrderByDateCreatedDesc("sagarkalra03@gmail.com", pageable).getContent();
		
		for(Transaction transaction: transactions) {
			System.out.println("tracking no ----"+transaction.getTrackingNumber());
		}
	}
	
	@Test
	public void getTransactionDetails() {
		Transaction transaction= transactionRepository.findById(9);
		System.out.println(transaction.getOrderItems().size());
		System.out.println(transaction.getCustomer().getEmail());
		System.out.println(transaction.getAddress().getStreet());
	}

}
