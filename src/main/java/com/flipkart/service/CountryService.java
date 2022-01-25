package com.flipkart.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.flipkart.dto.CountryDto;
import com.flipkart.dto.ResponseEntity;

public interface CountryService {
	
	public ResponseEntity<List<CountryDto>> getAllCountries();
	
	public ResponseEntity<String> getCountryCode(String country);

}
