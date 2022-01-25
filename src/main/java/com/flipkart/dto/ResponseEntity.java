package com.flipkart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity<T> {
	
	private Status status;
	
	private String message;
	
	private T entity;
	
	private PageInfo pageInfo;
	
	public ResponseEntity() {
		this.status= Status.FAIL;
		this.message="";
		this.entity= null;
		this.pageInfo= null;
	}

	
	
	
	
	

}
