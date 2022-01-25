package com.flipkart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	public List<Address> findByCustomerEmail(String email);

}
