package com.lms.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Purpose;
import com.lms.repo.PurposeRepository;

@RestController
@CrossOrigin("*")
public class PurposeController {

	@Autowired
	private PurposeRepository purposeRepository;
	
	@GetMapping("/purpose/list")
	public List<Purpose> getPurposes(){
		return purposeRepository.findAll();
	}
}
