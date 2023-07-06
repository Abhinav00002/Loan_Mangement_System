package com.lms.controller.customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
		
	//get data for renewal update to loan id
			@GetMapping("/renewal-data/{leadId}")
			 public Map<String, Object> getLeadData(@PathVariable Integer leadId) {
				 // Retrieve the authentication object from the security context
			    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			    // Get the user details from the authentication object
			    User userDetails = (User) authentication.getPrincipal();
			    // Get the current user's ID as a Long
			    Integer userId =  (int) userDetails.getId();
			    // Get the current user's name
			    String username = userDetails.getUsername();
			    
			    
			    List<Lead> leads = leadRepository.findByleadID(leadId);
			    Map<String, Object> result = new HashMap<>();
		
			    if (!leads.isEmpty()) {
			        Lead lead = leads.get(0);
			      //retrive loan details
		        	List<LoanCreation> loan=loanCreationRepository.getLoanCreationByleadid( lead.getLeadID());
		        	 
			       
			        int borrowerId = lead.getBorrowerID();
			        List<Customer> borrower = customerRepository.findBycid(borrowerId);
			        int coborrowerId=lead.getCoBorrowerId();
			        List<Customer> coBorrower=customerRepository.findBycid(coborrowerId);
			        result.put("borrower", borrower);
			        result.put("coBorrower",coBorrower);
			        result.put("loan", loan);
			     
			        
			        // Check if a renewal loan already exists for the given lead ID
			        boolean renewalLoanExists = renewalLoanRepository.existsByloanID(leadId);
			        if (renewalLoanExists) { 
			        	
			        	result.put("error", "Renewal loan already created for this lead ID");
			          
			        	return result;
			        
			        }else {
			       
			        	
			        RenewalLoan renewalLoan = new RenewalLoan();
			        
			        renewalLoan.setLoanID(leadId);
			        renewalLoan.setSourcedBy(userId);
			        
			        renewalLoanRepository.save(renewalLoan);
			        
			        
			        LocalDate entryDate = renewalLoan.getEntryDate();
			        String entryDate1 = entryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			        Lead lead2=new Lead();
			        
			        lead2.setBorrowerID(borrowerId);
			        lead2.setCoBorrowerId(coborrowerId);
			        lead2.setAplicationDate(entryDate1);
			        lead2.setBranchID(lead.getBranchID());
			        lead2.setCenterID(lead.getCenterID());
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
//			@PostMapping("/save/renewal/")
//			public RenewalLoan createRenewalSave(@RequestBody RenewalLoan saveRenewal) {
//				return renewalLoanRepository.save(saveRenewal);
//				
//			}
}
