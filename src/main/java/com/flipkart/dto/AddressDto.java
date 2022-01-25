package com.flipkart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
	
	private long addressId;

	private String zipCode;

	private String country;

	private String state;

	private String city;

	private String street;

}
