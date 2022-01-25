package com.flipkart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flipkart.entity.State;

public interface StateRepository extends JpaRepository<State, Integer>{
	
	public List<State> findByCountryCountryId(int countryId); 

}
