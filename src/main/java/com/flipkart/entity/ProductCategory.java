package com.flipkart.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductCategory {
	@Id
	@SequenceGenerator(name = "product_category_id", sequenceName = "product_category_seq")
	@GeneratedValue(generator = "product_category_id", strategy = GenerationType.IDENTITY)
	private long categoryId;
	@Column
	private String categoryName;
	
	@OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Product> listOfProducts= new HashSet<>();
	
	public void add(Product product) {
		if(product!=null) {
			listOfProducts.add(product);
			product.setProductCategory(this);
		}
	}

}
