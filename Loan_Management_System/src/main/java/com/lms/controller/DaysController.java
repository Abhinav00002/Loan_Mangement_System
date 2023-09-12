package com.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Days;
import com.lms.repo.DaysRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/days")
public class DaysController {

	// @Autowired
	// private DaysService daysService;
	@Autowired
	private DaysRepository daysRepository;

	@GetMapping("/list")
	public List<Days> getDays() {
		List<Days> list = new ArrayList<Days>();
		list = daysRepository.findAll();
		return list;
	}

}
