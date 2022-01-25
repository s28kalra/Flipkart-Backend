package com.flipkart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
	
	private long orderItemId;

	private int quantity;

	private long productId;
	
	private String name;
	
	private String imageUrl;
	
	private double unitPrice;

}
