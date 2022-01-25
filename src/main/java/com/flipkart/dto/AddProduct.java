package com.flipkart.dto;

import com.flipkart.entity.Product;
import com.flipkart.entity.ProductCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProduct {
	
	private Product product;
	
	private ProductCategory category;
	
}
