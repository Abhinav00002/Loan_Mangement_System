package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.EmiType;
import com.lms.repo.EmiRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/emi")
public class EmiTypeController {

	@Autowired
	private EmiRepository emiRepository;

	@GetMapping("/list")
	public List<EmiType> geEmiTypes() {
		return emiRepository.findAll();
	}
}
