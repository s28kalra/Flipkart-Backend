package com.flipkart.dto;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.entity.Address;
import com.flipkart.entity.Customer;
import com.flipkart.entity.OrderItem;
import com.flipkart.entity.Transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Purchase {
	
	private Customer customer;
	private Address address;
	private Transaction transaction;
	private List<OrderItem> orderItems= new ArrayList<OrderItem>();

}
