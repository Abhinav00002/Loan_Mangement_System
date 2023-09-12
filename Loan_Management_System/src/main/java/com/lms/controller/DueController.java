package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Due;
import com.lms.repo.DueRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/due")
public class DueController {

	@Autowired
	private DueRepository dueRepository;

	@GetMapping("/list")
	public List<Due> getDues() {
		return dueRepository.findAll();
	}

}
