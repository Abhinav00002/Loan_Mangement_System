package com.lms.controller.customer;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.repo.InvestmentRepository;
import com.lms.service.impl.UserServiceImpl;

@CrossOrigin("*")
@RestController
public class TotalInterest {

	@Autowired
	private InvestmentRepository investmentRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;
	

	@GetMapping("/totalInterest/list/")
	public ResponseEntity<List<Map<String, Object>>> findBytoDateTofromDate(
	        @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	        @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	        Principal principal) {
		System.out.println(toDate +"  fghfghfg"+ fromDate);

	    String username = principal.getName();
	    Integer userBranchId = userServiceImpl.getUserBranchId(username);
	    int userRank = userServiceImpl.getUserRank(username); 

	     if (!userServiceImpl.isUserAuthorized(username, userRank, userBranchId)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }

	     if (userRank == 1 && userBranchId == 1) {
	        List<Map<String, Object>> loan = investmentRepository.getSumOfInterestAndPF(toDate, fromDate);
	        return ResponseEntity.ok(loan);
	    }

	     if (userRank == 2 || (userBranchId != 1 && userRank == 1)) {
	        List<Map<String, Object>> loan = investmentRepository.getSumOfInterestAndPFByBranch(userBranchId, toDate, fromDate);
	        return ResponseEntity.ok(loan);
	    }

	    // If user rank is 3, they are not permitted to access disbursement data
	    if (userRank == 3) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }

	    // For any other cases, return forbidden status
	    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}



	
}
