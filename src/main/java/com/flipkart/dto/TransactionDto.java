package com.flipkart.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {
	
	private long transactionId;

	private String trackingNumber;

	private double totalPrice;

	private int totalQuantity;

	private String status;

	private Date dateCreated;
	
	private AddressDto address= new AddressDto();
	
	private CustomerDto customer= new CustomerDto();
	
	private List<OrderItemDto> orderItems= new ArrayList<OrderItemDto>();

}
