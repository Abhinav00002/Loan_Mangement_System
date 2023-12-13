package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.lms.model.Investment;
import com.lms.repo.InvestmentRepository;

@RestController
@CrossOrigin("*")
public class InvestmentController {
	
	@Autowired
	private InvestmentRepository investmentRepository;
	
	@PostMapping("/investment/save/")
	public Investment saveInvestment(@RequestBody Investment investment) { 
		Investment newInvestment=investmentRepository.save(investment);
				return newInvestment;
	}

	
	@GetMapping("/investmentReport/list/")
	public List<Investment> getInvestment() {
		List<Investment> allInvest=investmentRepository.getAllInvestments();
		return allInvest;
		
	}
}
