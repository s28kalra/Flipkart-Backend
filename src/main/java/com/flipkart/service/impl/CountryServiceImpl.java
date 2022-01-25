package com.flipkart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flipkart.dto.CountryDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.Status;
import com.flipkart.entity.Country;
import com.flipkart.repository.CountryRepository;
import com.flipkart.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	private ResponseEntity<List<CountryDto>> getResponseOfCountries(List<Country> countries) {
		ResponseEntity<List<CountryDto>> response = new ResponseEntity<List<CountryDto>>();
		List<CountryDto> list = new ArrayList<CountryDto>();
		if (!countries.isEmpty()) {
			response.setStatus(Status.SUCCESS);
			for (Country country : countries) {
				CountryDto dto = new CountryDto();
				BeanUtils.copyProperties(country, dto);
				list.add(dto);
			}
			response.setEntity(list);
		} else {
			response.setMessage("Countries not Fetched");
		}
		return response;
	}

	@Override
	public ResponseEntity<List<CountryDto>> getAllCountries() {
		return getResponseOfCountries(countryRepository.findAll());

	}

	@Override
	public ResponseEntity<String> getCountryCode(String countryName) {
		ResponseEntity<String> response = new ResponseEntity<String>();
		Country country = countryRepository.findByNameContaining(countryName);
		if (country != null) {
			response.setStatus(Status.SUCCESS);
			response.setEntity(country.getCode());
		}
		return response;
	}

}
