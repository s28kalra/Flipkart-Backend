package com.flipkart.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.flipkart.dto.AddProduct;
import com.flipkart.dto.ProductDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.entity.Product;

public interface ProductService {
	
	public ResponseEntity<List<ProductDto>> getAllProducts(Pageable pageable);
	
	public ResponseEntity<ProductDto> getProductWithId(long productId);
	
	public ResponseEntity<List<ProductDto>> getProductsByCategoryId(long categoryId, Pageable pageable);
	
	public ResponseEntity<List<ProductDto>> getProductsBySearching(String name, Pageable pageable);
	
	public boolean addProduct(AddProduct addProduct, MultipartFile mainImage, List<MultipartFile> secondaryImages);
	
}
