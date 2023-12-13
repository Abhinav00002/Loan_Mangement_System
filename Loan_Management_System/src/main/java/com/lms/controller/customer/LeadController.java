package com.lms.controller.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public List<Map<String, Object>> getLeads(){
		List<Map<String, Object>> leadList=  leadRepository.findlead();
//		System.out.println(leadList);
		return leadList;
	}

}
