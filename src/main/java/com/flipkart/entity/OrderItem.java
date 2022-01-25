package com.flipkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class OrderItem {

	@Id
	@SequenceGenerator(name = "order_item_id", sequenceName = "order_item_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "order_item_id", strategy = GenerationType.IDENTITY)
	private long orderItemId;

	@Column
	private int quantity;

	@Column
	private long productId;
	
	@Column
	private String imageUrl;
	
	@Column
	private double unitPrice;
	
	@Column
	private String name;

	@JoinColumn(name = "transaction_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Transaction transaction;

}
