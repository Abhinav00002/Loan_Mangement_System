package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Kyc;
import com.lms.repo.KycRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/kyc")
public class KycController {

	@Autowired
	private KycRepository kycRepository;

	@GetMapping("/list")
	public List<Kyc> getKycs() {
		return kycRepository.findAll();

	}
}
