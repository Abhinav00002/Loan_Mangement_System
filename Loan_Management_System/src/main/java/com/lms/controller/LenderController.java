package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Lender;
import com.lms.repo.LenderRepository;

@RestController
@CrossOrigin("*")
public class LenderController {
	
	@Autowired
	private LenderRepository lenderRepository;
	
	@GetMapping("/lender/list")
	public List<Lender> getAllLender() {
		return lenderRepository.findAll();
	}
	
	 @PostMapping("/lender/save/")
	    public ResponseEntity<?> saveLender(@RequestBody Lender lender) {
		 if (lender.getLenderName() == null || lender.getLenderName().isEmpty() || lender.getLenderPannumber() == null || lender.getLenderPannumber().isEmpty()) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lender Name or Lender Pan Number cannot be null or empty.");
		    } else if (lender.getLenderMobnumber().length() != 10) { 
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lender Contect Number must be exactly 10 characters long.");
	        }
		 

	        Lender savedLender = lenderRepository.save(lender);
	        return ResponseEntity.ok(savedLender);
	    }
}
