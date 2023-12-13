package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.lms.model.Salary;
import com.lms.repo.SalaryRepository;

@CrossOrigin("*")
@RestController
public class SalaryController {

	@Autowired
	private SalaryRepository salaryRepository;
	
	@PostMapping("/salary/save/")
	public Salary saveSalary(@RequestBody Salary salary) {
		 
		salary.setEntryDate(salary.getSalaryDate());
			salaryRepository.save(salary);
		 
		return  salary;
	}
	
	
	
}
