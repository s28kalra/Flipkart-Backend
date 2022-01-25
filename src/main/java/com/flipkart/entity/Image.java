package com.flipkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {
	
	@Id
	@SequenceGenerator(name = "image_id", sequenceName = "image_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "image_id", strategy = GenerationType.IDENTITY)
	private long imageId;
	
	@Column
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn (name = "product_id")
	private Product product;

	
}
