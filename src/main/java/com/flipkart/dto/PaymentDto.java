package com.flipkart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
	
	private int amount;
	private String currency;
	private String receiptEmail;

}
