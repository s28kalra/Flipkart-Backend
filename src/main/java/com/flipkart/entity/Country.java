package com.flipkart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {
	
	@Id
	@Column(name = "id")
	private int countryId;
	
	@Column (name = "code")
	private String code;
	
	@Column (name = "name")
	private String name;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<State> states= new ArrayList<State>();
	
	public void add(State state) {
		if(state!=null) {
			states.add(state);
			state.setCountry(this);
		}
	}
	

}
