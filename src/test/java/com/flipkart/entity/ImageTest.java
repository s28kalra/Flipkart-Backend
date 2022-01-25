package com.flipkart.entity;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flipkart.controller.MainController;
import com.flipkart.dto.ImageDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.entity.Image;
import com.flipkart.entity.Product;
import com.flipkart.repository.ImageRepository;
import com.flipkart.repository.ProductCategoryRepository;
import com.flipkart.repository.ProductRepository;

@SpringBootTest
class ImageTest {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MainController controller;
	
	long productId= 952l;
	
	@Test
	void addAnImage() {
		int numberOfImages=8;
		
		Product product = productRepository.getById(productId);
		if (product != null) {
			for(int i=0;i<numberOfImages;i++) {
				Image image= new Image();
				image.setProduct(product);
				imageRepository.save(image);
				}
		}
	}
	
	@Test
	public void updateImageUrl(){
		String extension=".jpeg";
		Product product= productRepository.getProductWithAllImages(productId);
		String baseUrl="http://localhost:8080/image/";
		List<Image> images= product.getImages();
		for(Image image: images) {
			String s= baseUrl+ image.getImageId() + extension;
			image.setImageUrl(s);
		}
		imageRepository.saveAll(images);
	}
	
	
	

}
