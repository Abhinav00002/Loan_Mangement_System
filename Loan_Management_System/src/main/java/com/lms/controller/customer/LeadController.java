package com.lms.controller.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Lead;
import com.lms.repo.LeadRepository;


@RestController
@RequestMapping("/lead")
@CrossOrigin("*")

public class LeadController {
	
	
	
	@Autowired
	private LeadRepository leadRepository;
	
	@GetMapping("/list")
	public List<Lead> getLeads(){
		List<Lead> leadList=new ArrayList<Lead>();
		leadList=leadRepository.findAll();
		return leadList;
	}

}
