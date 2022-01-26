package com.flipkart.entity;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.flipkart.controller.MainController;
import com.flipkart.repository.CustomerRepository;

@SpringBootTest
@Rollback
public class AddressTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MainController controller;
	
	
	private void setAddress(Address address) {
		address.setZipCode("136027");
		address.setCountry("India");
		address.setState("Haryana");
		address.setCity("Kaithal");
		address.setStreet("Siwan Gate");
	}
	
	private void setCustomer(Customer customer) {
		customer.setFirstName("Sagar");
		customer.setLastName("Kalra");
		customer.setMobile("7988458317");
		customer.setEmail("sagarkalra03@gmail.com");
		
	}
	
	@Test
	public void getAddress() {
		controller.getListOfAddresses("sagarkalra03@gmail.com");
	}
	
	@Test
	public void testAddressDeletion() {
		Customer customer= new Customer();
		setCustomer(customer);
		customer.setCustomerId(2);
		
		Address address= new Address();
		setAddress(address);
		address.setCustomer(customer);
		customer.setAddresses(new ArrayList<>(Arrays.asList(address)));
	}

}
