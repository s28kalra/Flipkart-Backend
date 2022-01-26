package com.flipkart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "state")
public class State {

	@Id
	@Column(name = "id")
	private int stateId;

	@Column(name = "name")
	private String name;

	@JoinColumn(name = "country_id")
	@ManyToOne( fetch =  FetchType.LAZY)
	private Country country;
	
	State(int stateId, String name){
		this.stateId= stateId;
		this.name= name;
	}

	
}
