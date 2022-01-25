package com.flipkart.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.flipkart.entity.Product;
import com.flipkart.repository.ProductRepository;
import com.flipkart.utils.FlipkartUtils;

@SpringBootTest
class GeneralUtilsTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test 
	public void testMatcher() {
		String pattern ="/transaction*";
		String matcher= "/transactions?email=sagarkalra03@gmail.com";
		System.out.println(Pattern.matches(pattern, matcher));
	}
	
	
	

}
