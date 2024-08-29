package com.lms.controller.customer;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.repo.InvestmentRepository;
import com.lms.service.impl.UserServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/totalInterest")
public class TotalInterestController {

	@Autowired
	private InvestmentRepository investmentRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;


	
	
	@GetMapping("/profit/list/")
	public ResponseEntity<List<Map<String, Object>>> findBytoDateTofromDate(
	        @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	        @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	        Principal principal) {
	    System.out.println("DATE : " + toDate + "from Date: " + fromDate);
	    String username = principal.getName();
	    Integer userBranchId = userServiceImpl.getUserBranchId(username);
	    int userRank = userServiceImpl.getUserRank(username);

	    if (!userServiceImpl.isUserAuthorized(username, userRank, userBranchId)) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }

	    List<Map<String, Object>> sumOfPrincipleAndInterest;
	    List<Map<String, Object>> sumOfPf;
	    List<Map<String, Object>> getSumOfSalary;
	    List<Map<String, Object>> getSumOfBranchExpences;

	    if (userRank == 1 && userBranchId == 1) {
	        sumOfPrincipleAndInterest = investmentRepository.getSumOfInterestAndPrinciple(toDate, fromDate);
	        sumOfPf = investmentRepository.getSumOfPF(toDate, fromDate);
	        getSumOfSalary = investmentRepository.getSumOfSalary(toDate, fromDate);
	        getSumOfBranchExpences = investmentRepository.getSumOfbranchExpences(toDate, fromDate);
	    } else if (userRank == 2 || (userBranchId != 1 && userRank == 1)) {
	        sumOfPrincipleAndInterest = investmentRepository.getSumOfInterestAndPrincipleByBranch(userBranchId, toDate, fromDate);
	        sumOfPf = investmentRepository.getSumOfPFByBranch(userBranchId, toDate, fromDate);
	        getSumOfSalary = investmentRepository.getSumOfSalary(toDate, fromDate);
	        getSumOfBranchExpences = investmentRepository.getSumOfbranchExpencesByBranch(userBranchId, toDate, fromDate);
	    } else if (userRank == 3) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    } else {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }

	    // Combine and aggregate data from all lists
	    List<Map<String, Object>> combinedData = combineData(sumOfPrincipleAndInterest, sumOfPf, getSumOfSalary, getSumOfBranchExpences);

	    return ResponseEntity.ok(combinedData);
	}

	private List<Map<String, Object>> combineData(List<Map<String, Object>> sumOfPrincipleAndInterest,
	                                              List<Map<String, Object>> sumOfPf,
	                                              List<Map<String, Object>> getSumOfSalary,
	                                              List<Map<String, Object>> getSumOfBranchExpences) {
	    List<Map<String, Object>> combinedData = new ArrayList<>();
	    
	    Map<String, Map<String, Object>> aggregatedData = new LinkedHashMap<>();

	    // Aggregate data from sumOfPf
	    aggregateData(aggregatedData, sumOfPf);
	    aggregateData(aggregatedData, getSumOfSalary);
	    aggregateData(aggregatedData, sumOfPrincipleAndInterest);
	    aggregateData(aggregatedData, getSumOfBranchExpences);

	    // Convert aggregated data to list
	    for (Map.Entry<String, Map<String, Object>> entry : aggregatedData.entrySet()) {
	        combinedData.add(entry.getValue());
	    }

	    return combinedData;
	}

	private void aggregateData(Map<String, Map<String, Object>> aggregatedData, List<Map<String, Object>> dataList) {
	    for (Map<String, Object> map : dataList) {
	        String monthYear = (String) map.get("Month_Year");
	        if (!aggregatedData.containsKey(monthYear)) {
	            aggregatedData.put(monthYear, new HashMap<>());
	        }
	        aggregatedData.get(monthYear).putAll(map);
	    }
	}


}
