package com.flipkart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flipkart.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	public Page<Transaction> findByCustomerEmailOrderByDateCreatedDesc(String email, Pageable pageable);

	@EntityGraph(attributePaths = { "customer", "address", "orderItems" })
	public Transaction findById(long transactionId);
	
	@EntityGraph(attributePaths = { "customer", "address", "orderItems" })
	public Transaction findByTrackingNumber(String trackingNumber);

}
