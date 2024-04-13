package com.lms.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Deposit;
import com.lms.model.foreclose.ForeClose;
import com.lms.repo.ForecloseRepository;

@RestController
@CrossOrigin("*")
public class ForeCloseController {

	@Autowired
	private ForecloseRepository forecloseRepository;
	
	@GetMapping("/foreclose/due/{loanId}")
	public  Map<String, Object>  getForeCloseDues(@PathVariable("loanId") Integer loanId){
		 Map<String, Object>  foreclosedue=forecloseRepository.getForeCloseDues(loanId);
		return foreclosedue;
	}
	
	

	@PostMapping("/foreclose/save/")
	 public ResponseEntity<?> createForeclose(@RequestBody ForeClose foreClose) { 
		System.out.println("FORECLOSE : "+ foreClose);
        if (foreClose.getLoanId() == 0 || foreClose.getCollAmount() == 0 || foreClose.getCollDate() == null) {
            
            return ResponseEntity.badRequest().body("LoanId, CollAmount, and CollDate cannot be null or empty.");
            
        }  
        foreClose.setStatus(1);
        
        ForeClose foreclosesave=forecloseRepository.save(foreClose);

        return ResponseEntity.ok().body("{\"message\": \"Foreclose saved successfully.\"}");

    }

	
	@GetMapping("/foreclose/list/")
	public ResponseEntity<List<Map<String, Object>>> findBytoDateTofromDate(
			@RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
		List<Map<String, Object>> ForecloseData = forecloseRepository.findByddateBetween(toDate, fromDate);
		System.out.println(ForecloseData);
		return ResponseEntity.ok(ForecloseData);
	}

}
