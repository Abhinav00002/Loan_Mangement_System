package com.lms.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Eduaction;
import com.lms.repo.EducationRepository;

@RestController
@CrossOrigin("*")

public class EducationController {

	
	
	@Autowired
	private EducationRepository educationRepository;
	
	
	@GetMapping("/education/list")
	public List<Eduaction> getEduactions(){
		return educationRepository.findAll();
	}
}
