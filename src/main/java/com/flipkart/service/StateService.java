package com.flipkart.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.StateDto;

public interface StateService {
	
	public ResponseEntity<List<StateDto>> getStatesByCountryId(int countryId);

}
