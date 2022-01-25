package com.flipkart.service;

import java.util.List;

import com.flipkart.dto.AddressDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.entity.Address;

public interface AddressService {
	
	public ResponseEntity<List<AddressDto>> getListOfAddresses(String email);
	
	public boolean AddAddress(Address address, String email);

}
