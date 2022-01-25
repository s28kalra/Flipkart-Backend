package com.flipkart.entity;

import java.util.ArrayList;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

	@Id
	@SequenceGenerator(name = "address_id", sequenceName = "address_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "address_id", strategy = GenerationType.IDENTITY)
	private long addressId;

	@Column
	private String zipCode;

	@Column
	private String country;

	@Column
	private String state;

	@Column
	private String city;

	@Column
	private String street;

	@JoinColumn(name = "customer_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Transaction> transactions = new ArrayList<Transaction>();

}
