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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Transaction {

	@Id
	@GeneratedValue(generator = "transaction_id", strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "transaction_id", sequenceName = "transaction_seq", allocationSize = 1, initialValue = 1)
	private long transactionId;

	@Column
	private String trackingNumber;

	@Column
	private double totalPrice;

	@Column
	private int totalQuantity;

	@Column
	private String status;

	@Column
	@CreationTimestamp
	private Date dateCreated;

	@Column
	@UpdateTimestamp
	private Date lastUpdated;

	@JoinColumn(name = "customer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

	@JoinColumn(name = "address_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Address address;

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public void add(OrderItem item) {
		if(item!=null) {
			orderItems.add(item);
			item.setTransaction(this);
		}
	}

}
