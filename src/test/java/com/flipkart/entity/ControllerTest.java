package com.flipkart.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flipkart.controller.MainController;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.TransactionDto;

@SpringBootTest
class ControllerTest {

	@Autowired
	private MainController controller;
	
	@Test
	void testGetTransactionDetails() {
		String trackingNumber="e682c05f-70ea-4749-abf4-fde9a78ef3f3";
		ResponseEntity<TransactionDto> response= controller.getTransactionDetail(trackingNumber);
		System.out.println(response.getEntity().getCustomer());
		System.out.println(response.getEntity().getAddress());
		System.out.println(response.getEntity().getOrderItems());
		
	}

}
