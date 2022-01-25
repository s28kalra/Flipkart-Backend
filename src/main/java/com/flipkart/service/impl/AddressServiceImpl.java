package com.flipkart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.flipkart.dto.AddressDto;
import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.Status;
import com.flipkart.entity.Address;
import com.flipkart.repository.AddressRepository;
import com.flipkart.repository.CustomerRepository;
import com.flipkart.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private ResponseEntity<List<AddressDto>> getResponseOfAddresses(List<Address> addresses) {
		ResponseEntity<List<AddressDto>> response = new ResponseEntity<List<AddressDto>>();
		List<AddressDto> list = new ArrayList<AddressDto>();
		if (!CollectionUtils.isEmpty(addresses)) {
			response.setStatus(Status.SUCCESS);
			for (Address address : addresses) {
				AddressDto dto = new AddressDto();
				BeanUtils.copyProperties(address, dto);
				list.add(dto);
			}
		} else {
			response.setMessage("No Address added yet");
		}
		response.setEntity(list);
		return response;
	}

	@Override
	public ResponseEntity<List<AddressDto>> getListOfAddresses(String email) {
		return getResponseOfAddresses(addressRepository.findByCustomerEmail(email));

	}

	@Override
	@Transactional
	public boolean AddAddress(Address address, String email) {
		address.setCustomer(customerRepository.findByEmail(email));
		address = addressRepository.save(address);
		if (address.getAddressId() > 0)
			return true;
		return false;

	}

}
