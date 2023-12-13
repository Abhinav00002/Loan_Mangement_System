package com.lms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;
import com.lms.model.Scheme;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.LoanRepaymentRepository;
import com.lms.repo.SchemeRepository;

@RestController
@CrossOrigin("*")

public class SchemeController {

	@Autowired
	private SchemeRepository schemeRepository;
	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
	@Autowired
	private LoanCreationRepository loanCreationRepository;

	@PostMapping("/scheme/save")

	public Scheme createScheme(@RequestBody Scheme scheme) throws Exception {
		schemeRepository.save(scheme);
		return scheme;
	}

	@GetMapping("/scheme/list")
	public List<Scheme> getSchemes() {
		return schemeRepository.findAll();
	}
	
	
	//SCHEME CHANGE
	
	@PostMapping("/scheme/change/{loanId}/{schemeno}")
	public ResponseEntity<?> changeScheme(
			@PathVariable("schemeno") Integer schemeno,
			@PathVariable("loanId") Integer loanId) {

	    Optional<LoanCreation> loan = loanCreationRepository.findById((long) loanId);

	    if (!loan.isPresent()) {
	    	System.out.println("Loan Id not found");
	        return ResponseEntity.badRequest().body("Loan Id not found");
	    }

	    LoanCreation loan1 = loan.get();
	    loan1.setScheme(schemeno);
	    loanCreationRepository.save(loan1);

	    Scheme scheme = schemeRepository.findById(schemeno);

	    if (scheme == null) {
	        return ResponseEntity.badRequest().body("New scheme not found");
	    }

	    double loanAmount = scheme.getLoneamount();
	    double interestRate = scheme.getIntrestrate();
	    int emiType = scheme.getEmitype();
	    double emi = scheme.getEmi();
	    double tenor = scheme.getTenor();
	    double pfAmount = scheme.getPfamount();
	    double insuranceAmount = scheme.getInsuranceamount();
	    long schemeBy = scheme.getSchemeBy();
	    double irr = scheme.getIrr();

	    LocalDate dueDate = loan1.getMeetingDate();
	    int branchId = loan1.getBranchname();
	    int centerId = loan1.getCentername();
	    double remainingAmount = loanAmount;

	    List<LoanRepayment> existingLoanRepayments = loanRepaymentRepository.findByLoanId(loanId);
	    int oldTenor = existingLoanRepayments.size();

	 // Create a map to store existing loan repayment records for quick access
	    Map<Integer, LoanRepayment> existingRecordsMap = new HashMap<>();
	    for (LoanRepayment existingRecord : existingLoanRepayments) {
	        existingRecordsMap.put((int) existingRecord.getInstallmentNo(), existingRecord);
	    }


	    // Create a list to store updated loan repayment records
	    List<LoanRepayment> updatedLoanRepayments = new ArrayList<>();

	    for (int i = 1; i <= tenor; i++) {
	        if (i > 1) {
	            if (emiType == 3) {
	                dueDate = dueDate.plusDays(14);
	            } else if (emiType == 2) {
	                dueDate = dueDate.plusDays(7);
	            } else if (emiType == 1) {
	                dueDate = dueDate.plusMonths(1);
	            }
	        }

	        LoanRepayment loanRepayment = existingRecordsMap.get(i);

	        if (loanRepayment == null) {
	            // If no existing record found for this installment, create a new one
	            loanRepayment = new LoanRepayment();
	            loanRepayment.setLoanId(loanId);
	            loanRepayment.setInstallmentNo(i);
	        }

	        // Calculate Interest and principal
	        double interest = remainingAmount * irr;
	        double principal = emi - interest;

	        // Update the loan repayment record
	        loanRepayment.setDueDate(dueDate);
	        loanRepayment.setIntrest(interest);
	        loanRepayment.setEmi(emi);
	        loanRepayment.setCollectionBy(schemeBy);
	        loanRepayment.setPrinciple(principal);
	        loanRepayment.setStatus(1);
	        loanRepayment.setOpenningAmount(remainingAmount);
	        loanRepayment.setBranchId(branchId);
	        loanRepayment.setCenterId(centerId);
	        
	        updatedLoanRepayments.add(loanRepayment);
	        remainingAmount -= principal;
	    }
	    if (tenor < oldTenor) {
	        for (int i = (int) (tenor + 1); i <= oldTenor; i++) {
	            LoanRepayment loanRepaymentToRemove = existingRecordsMap.get(i);
	            if (loanRepaymentToRemove != null) {
	                // Remove the excess record from the database
	                loanRepaymentRepository.delete(loanRepaymentToRemove);
	            }
	        }
	    }

	    // Update or save loan repayment records
	    loanRepaymentRepository.saveAll(updatedLoanRepayments);

	    return ResponseEntity.ok("Scheme changed successfully !! \n For Loan Id "+loanId);
	}

}
