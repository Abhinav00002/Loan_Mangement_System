package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.States;
import com.lms.repo.StateRepository; 



@RestController
@CrossOrigin("*")
@RequestMapping("/state")
public class StateController {

	 
	 
	
	@Autowired
	private StateRepository stateRepository;
	
	
	
	
	
	
	//Get All States
	@GetMapping("/list")
	public List<States> getStates(){
		return stateRepository.findAll();
	}
	
	
	//Get State By Id
		@GetMapping("/list/{statesId}")
		public States getStates(@PathVariable("statesId") Integer statesId) {
			return this.stateRepository.getStatesById(statesId);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

//		@GetMapping(value="/{statename}")
//		public States getStates(@PathVariable("statename") String statename) {
//			return this.statename.getState(statename);
//			
//		}	
}
