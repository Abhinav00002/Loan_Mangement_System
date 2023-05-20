package com.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Center;
import com.lms.model.address.District;
import com.lms.repo.CenterRepository; 

@RestController
@CrossOrigin("*")
@RequestMapping("/newCenter")
public class CenterController {

	
	@Autowired
	private CenterRepository centerRepository;
	
	
	@PostMapping("/")
	public Center createCenter(@RequestBody Center center) throws Exception {
		centerRepository.save(center);
		return center;
		
	}
	
	@GetMapping("/list/")
	public List<Center> getCenter(){
		List<Center> center= new ArrayList<Center>();
		center=centerRepository.findAll();
		return center;
	}
	
	
	
	
	//get district by branchId
		@GetMapping("/branch/{branch_name}")
		public List<Center> getCenterByBname(@PathVariable("branch_name") Long branch_name) {
			return  centerRepository.getCenterByBname(branch_name);
		}
}
