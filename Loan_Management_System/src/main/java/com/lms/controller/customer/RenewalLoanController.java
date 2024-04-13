package com.lms.controller.customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Customer;
import com.lms.model.Lead;
import com.lms.model.LoanCreation;
import com.lms.model.RenewalLoan;
import com.lms.model.User;
import com.lms.model.error.ErrorResponse;
import com.lms.repo.LeadRepository;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.RenewalLoanRepository;
import com.lms.repo.customer.CustomerRepository;

@RestController
@CrossOrigin("*")
public class RenewalLoanController {

	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private LoanCreationRepository loanCreationRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private RenewalLoanRepository renewalLoanRepository; 
	
		
	//Save data for renewal update to loan id
	@PostMapping("/save/{loanId}")
	public Map<String, Object> saveLeadData(@PathVariable Integer loanId) {
	    // Retrieve the authentication object from the security context
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      User userDetails = (User) authentication.getPrincipal();
	      Integer userId = (int) userDetails.getId();
	     String username = userDetails.getUsername();

	     LoanCreation  loans=loanCreationRepository.getLoanByid( loanId);
	        
	    Map<String, Object> result = new HashMap<>();

	    if (loans!=null){
	        List<Lead> leads = leadRepository.findByleadID(loans.getLeadid());
	       System.out.println(leads);
	       int borrowerId = leads.get(0).getBorrowerID();
	        List<Customer> borrower = customerRepository.findBycid(borrowerId);
	        int coborrowerId=leads.get(0).getCoBorrowerId();
	        List<Customer> coBorrower=customerRepository.findBycid(coborrowerId);
	    
	         boolean renewalLoanExists = renewalLoanRepository.existsByloanID(loanId);
	         if(renewalLoanRepository.IsLoancoutions(loanId)==1  ) {
		    	 
		    	  result.put("error", "Loan is in Caution  State.");
		    	  return result;
			 }
	        if (renewalLoanExists) {
	            result.put("error", "Renewal loan already created for this lead ID");
	            return result;
	        } else {
	            RenewalLoan renewalLoan = new RenewalLoan();
	            renewalLoan.setLoanID(loanId);
	            int sourced=  Integer.parseInt(leads.get(0).getManageBy());
	            renewalLoan.setSourcedBy(sourced);
	            renewalLoanRepository.save(renewalLoan);

	            LocalDate entryDate = renewalLoan.getEntryDate();
	            String entryDate1 = entryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	            Lead lead2 = new Lead();
	            lead2.setBorrowerID(borrowerId);
	            lead2.setCoBorrowerId(coborrowerId);
	            lead2.setAplicationDate(entryDate1);
	            lead2.setBranchID(leads.get(0).getBranchID());
	            lead2.setCenterID(leads.get(0).getCenterID());
	            lead2.setEntryBy(Integer.toString(renewalLoan.getSourcedBy()));
	            lead2.setSourcedBy(Integer.toString(renewalLoan.getSourcedBy()));
	            lead2.setManageBy(Integer.toString(renewalLoan.getSourcedBy()));
	            leadRepository.save(lead2);

	            result.put("newLeadId", lead2.getLeadID());
	            result.put("renewalID", renewalLoan.getId());

	            return result;
	        }
	    }

	    return result;
	}
			
			
			
			
//			
			@GetMapping("/list/renewal-data/{loanId}")
			 public Map<String, Object> getLeadData(@PathVariable Integer loanId) {
				 LoanCreation  loans=loanCreationRepository.getLoanByid( loanId);
	        	System.out.println(loanId);
		  System.out.println(loans);
		    Map<String, Object> result = new HashMap<>();
	
		    if (loans!=null) { 
		      //retrive loan details
		        List<Lead> leads = leadRepository.findByleadID(loans.getLeadid());
		       System.out.println(leads);
		        int borrowerId = leads.get(0).getBorrowerID();
		        List<Customer> borrower = customerRepository.findBycid(borrowerId);
		        int coborrowerId=leads.get(0).getCoBorrowerId();
		        List<Customer> coBorrower=customerRepository.findBycid(coborrowerId);
		        result.put("borrower", borrower);
		        result.put("coBorrower",coBorrower);
		        result.put("loan", loans);
		        }
			return result;
			}
	
}
