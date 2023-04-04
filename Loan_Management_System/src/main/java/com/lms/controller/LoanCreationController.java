package com.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.lms.model.LoanCreation;
import com.lms.repo.LoanCreationRepository; 



@RestController
@CrossOrigin("*")
@RequestMapping("/loanCreation")
public class LoanCreationController {

	
	@Autowired
	public LoanCreationRepository loanCreationRepository;
	
	
	@PostMapping("/")
	public LoanCreation createLoanCreation(@RequestBody LoanCreation loanCreation) throws Exception{
//		LoanCreation loan=loanCreationRepository.save(loanCreation);
		System.out.println(loanCreation);
		return null;
		
	}
	
	
	@GetMapping("/list")
	public List<LoanCreation> getLoanCreations(){
		List<LoanCreation> loanCreations=new ArrayList<LoanCreation>();
		loanCreations=loanCreationRepository.findAll();
		return loanCreations;
	}
}
