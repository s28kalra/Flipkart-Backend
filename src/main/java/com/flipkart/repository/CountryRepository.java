package com.flipkart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{
	
	public Country findByNameContaining(String anme);

}
