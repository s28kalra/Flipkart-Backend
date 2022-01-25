package com.flipkart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.dto.ProductCategoryDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.Status;
import com.flipkart.entity.ProductCategory;
import com.flipkart.repository.ProductCategoryRepository;
import com.flipkart.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl  implements ProductCategoryService{
	
	@Autowired
	private ProductCategoryRepository categoryRepository;

	@Override
	public ResponseEntity<List<ProductCategoryDto>> getAllcategories() {
		ResponseEntity<List<ProductCategoryDto>> response= new ResponseEntity<List<ProductCategoryDto>>();
		List<ProductCategory> categories= categoryRepository.findAll();
		List<ProductCategoryDto> dto= new ArrayList<ProductCategoryDto>();
		if(categories!=null && categories.size()>0) {
			response.setStatus(Status.SUCCESS);
			response.setMessage("Categories Fetched");
			for(ProductCategory c: categories) {
				ProductCategoryDto d= new ProductCategoryDto();
				BeanUtils.copyProperties(c, d);
				dto.add(d);
			}
			response.setEntity(dto);
		}else {
			response.setMessage("Categories not present");
		}
		return response;
	}

	

}
