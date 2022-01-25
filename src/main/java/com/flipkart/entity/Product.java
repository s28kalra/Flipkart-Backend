package com.flipkart.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id")
	@SequenceGenerator(name="product_id", sequenceName = "product_seq")
	private long productId;
	@Column
	private String sku; 
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private double unitPrice;
	@Column
	private String imageUrl;
	@Column
	private boolean active;
	@Column
	private int unitsInStock;
	@Column
	@CreationTimestamp
	private Date dateCreated;
	@Column
	@CreationTimestamp
	private Date lastUpdated;
	
	@JoinColumn (name = "category_id")
	@ManyToOne ( fetch = FetchType.LAZY)
	private ProductCategory productCategory;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Image> images= new ArrayList<>();
	
	public void addImage(Image image) {
		if(image!=null) {
			image.setProduct(this);
			images.add(image);
		}
	}
	
	

}
