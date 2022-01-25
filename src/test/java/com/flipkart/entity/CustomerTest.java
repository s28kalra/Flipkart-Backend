package com.flipkart.entity;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flipkart.repository.CustomerRepository;

@SpringBootTest
class CustomerTest {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void test() {
		System.out.println(customerRepository.findByEmail("sagarkalra03@gmail.com"));
	}
	
	@Test
	public void findAll() {
		List<Customer> customers= customerRepository.findAll();
		System.out.println(customers.size());
	}

}
