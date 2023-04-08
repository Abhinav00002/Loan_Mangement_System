package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Scheme;
import com.lms.repo.SchemeRepository;

@RestController
@CrossOrigin("*")

public class SchemeController {

	
	@Autowired
	private SchemeRepository schemeRepository;
	
	
	@PostMapping("/scheme/save")
	
      public Scheme createScheme(@RequestBody Scheme scheme) throws Exception{
          schemeRepository.save(scheme);
          return  scheme;
		}
	 
	
	@GetMapping("/scheme/list")
	public List<Scheme> getSchemes(){
		return schemeRepository.findAll();
		}
}
