package com.flipkart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.entity.Customer;
import com.flipkart.repository.CustomerRepository;
import com.flipkart.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public boolean setCustomer(String name, String email) {
		Customer customer= customerRepository.findByEmail(email);
		if(customer==null) {
			customer= new Customer();
			customer.setFirstName(name);
			customer.setEmail(email);
			customerRepository.save(customer);
			return true;
		}
		return false;
	}

	
}
