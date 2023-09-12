package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.PaymentMode;
import com.lms.repo.PaymentModeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/paymentMode")
public class PaymentModeController {

	@Autowired
	private PaymentModeRepository paymentModeRepository;

	@GetMapping("/list")
	public List<PaymentMode> getPaymentModes() {
		return paymentModeRepository.findAll();
	}
}
