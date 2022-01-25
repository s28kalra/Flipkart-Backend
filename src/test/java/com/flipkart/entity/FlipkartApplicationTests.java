package com.flipkart.entity;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flipkart.entity.Product;
import com.flipkart.entity.ProductCategory;
import com.flipkart.entity.Transaction;
import com.flipkart.repository.ProductCategoryRepository;
import com.flipkart.repository.ProductRepository;
import com.flipkart.utils.FlipkartUtils;

@SpringBootTest
class FlipkartApplicationTests {
	
	@Autowired
	private ProductCategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Test
	void getUrl() {
		System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").toUriString());
	}
	
	@Test
	public void addProductCategory() {
		ProductCategory category= new ProductCategory();
		category.setCategoryName("Sports_Shoes");
		category= categoryRepository.save(category);
		System.out.println(category);
	}
	
	@Test
	public void addProduct() {
		String name="Reebok Sports Shoes";
		String imageName="reebok-shoes.jpeg";
		long categoryId=252l;
		
		Product product= new Product();
		String baseUrl="http://localhost:8080/image/";
		product.setSku("POTS-34-BK");
		product.setName(name);
		product.setDescription(name+" , Makes your Life Smart");
		product.setUnitPrice(3000);
		product.setImageUrl(baseUrl.concat(imageName));
		product.setActive(true);
		product.setUnitsInStock(1000);
		product.setDateCreated(new Date());
		product.setLastUpdated(new Date());
		product.setProductCategory(categoryRepository.findById(categoryId).get());
		productRepository.save(product);
		System.out.println(product);
	}
	
	@Test
	public void getCategory() {
		ProductCategory category= categoryRepository.findById(1l).get();
		System.out.println(category.getCategoryName());
		System.out.println(category.getListOfProducts());
	}
	
	@Test
	public void getListOfProductsOfACategoryId(){
		int categoryId=1;
		ProductCategory category = categoryRepository.findCategoriesWithProducts((long) categoryId);
		if(category.getListOfProducts()!=null)
		for(Product p: category.getListOfProducts())
			System.out.println(p);	
	}
	
	

}
