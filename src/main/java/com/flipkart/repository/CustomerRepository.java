package com.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String theEmail);
}
