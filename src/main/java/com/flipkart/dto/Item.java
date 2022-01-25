package com.flipkart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
	
	private long productId;
	
	private int quantity;
	
	private double unitPrice;
	
	private String imageUrl;
	

}
