package com.flipkart.service;

import java.util.List;

import com.flipkart.dto.ProductCategoryDto;
import com.flipkart.dto.ResponseEntity;

public interface ProductCategoryService {
	
	
	  public ResponseEntity<List<ProductCategoryDto>> getAllcategories();
	  
	 
}
