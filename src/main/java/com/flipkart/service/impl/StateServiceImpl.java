package com.flipkart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.flipkart.dto.ResponseEntity;
import com.flipkart.dto.StateDto;
import com.flipkart.dto.Status;
import com.flipkart.entity.State;
import com.flipkart.repository.StateRepository;
import com.flipkart.service.StateService;
import com.flipkart.utils.FlipkartUtils;

@Service
public class StateServiceImpl implements StateService {
	
	@Autowired
	private StateRepository stateRepository;
	
	private ResponseEntity<List<StateDto>> getResponseOfStates(List<State> states){
		ResponseEntity<List<StateDto>> response = new ResponseEntity<List<StateDto>>();
		List<StateDto> list = new ArrayList<StateDto>();
		if (!states.isEmpty()) {
			response.setStatus(Status.SUCCESS);
			for (State state : states) {
				StateDto dto = new StateDto();
				BeanUtils.copyProperties(state, dto);
				list.add(dto);
			}
			response.setEntity(list);
		} else {
			response.setMessage("States not Fetched");
		}
		return response;
		
	}

	@Override
	public ResponseEntity<List<StateDto>> getStatesByCountryId(int countryId) {
		return getResponseOfStates(stateRepository.findByCountryCountryId(countryId));
	}

}
