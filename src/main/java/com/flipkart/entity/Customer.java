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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_id", sequenceName = "customer_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "customer_id", strategy = GenerationType.IDENTITY)
	private long customerId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String mobile;

	@Column (unique = true, nullable = false)
	private String email;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Address> addresses = new ArrayList<Address>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public void addTransaction(Transaction transaction) {
		if(transaction!=null) {
			transactions.add(transaction);
			transaction.setCustomer(this);
		}
	}
	
	public void addAddress(Address address) {
		if(address!=null) {
			addresses.add(address);
			address.setCustomer(this);
		}
	}

}
