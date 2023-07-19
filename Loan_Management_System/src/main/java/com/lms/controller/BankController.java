package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Bank; 
import com.lms.repo.BankRepository;

@RestController
@CrossOrigin("*")
public class BankController {


	@Autowired
	private BankRepository bankRepository;
	
	//get Banks
		@GetMapping("/Bank/list/")
		public  List<Bank> getBanks(){
			return bankRepository.findAll();
		}
}
