package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Time;
import com.lms.repo.TimeRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeRepository timeRepository;

	@GetMapping("/timelist")
	public List<Time> getTimes() {
		List<Time> time=timeRepository.findAll();
				System.out.println(time);
				return time;
	}
}
