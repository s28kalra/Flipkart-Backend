package com.flipkart.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flipkart.entity.Country;
import com.flipkart.entity.Product;
import com.flipkart.entity.ProductCategory;
import com.flipkart.entity.State;
import com.flipkart.entity.Transaction;

@Configuration
public class MyRestDataConfig implements RepositoryRestConfigurer, WebMvcConfigurer {
	
	@Value("${allowed.origins}") 
	private String[] origins;

	HttpMethod[] unsupportedActions = { HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.PATCH };

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

		// disable HTTP methods for Product: PUT, POST, DELETE
		disableHttpMethods(config, Product.class);
		disableHttpMethods(config, ProductCategory.class);
		disableHttpMethods(config, Country.class);
		disableHttpMethods(config, State.class);
		disableHttpMethods(config, Transaction.class);

	}

	private void disableHttpMethods(RepositoryRestConfiguration config, Class klass) {
		config.getExposureConfiguration().forDomainType(klass)
				.withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupportedActions))
				.withCollectionExposure((metadata, httpMethod) -> httpMethod.disable(unsupportedActions));
	}

/*	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("*").allowedOrigins(origins);
	}
*/	
	

}
