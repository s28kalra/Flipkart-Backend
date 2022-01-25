package com.flipkart.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProductDto {
	
	private long productId;
	
	private String sku; 
	
	private String name;
	
	private String description;
	
	private double unitPrice;
	
	private String imageUrl;
	
	private boolean active;
	
	private int unitsInStock;
	
	private Date dateCreated;
	
	private Date lastUpdated;
	
	private List<ImageDto> images;


}
