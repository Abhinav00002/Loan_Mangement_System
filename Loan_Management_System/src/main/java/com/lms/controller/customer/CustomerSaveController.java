package com.lms.controller.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Branch;
import com.lms.model.Center;
import com.lms.model.Customer;
import com.lms.model.CustomerSave;
import com.lms.model.Lead;
import com.lms.model.LoanCreation;
import com.lms.model.address.Days;
import com.lms.model.address.Time;
import com.lms.repo.BranchRepository;
import com.lms.repo.CenterRepository;
import com.lms.repo.DaysRepository;
import com.lms.repo.LeadRepository;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.TimeRepository;
import com.lms.repo.customer.CustomerRepository; 
@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerSaveController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private CenterRepository centerRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private TimeRepository timeRepository;
	@Autowired
	private DaysRepository daysRepository;
	@Autowired
	private LoanCreationRepository loanCreationRepository;
	 
	
	@PostMapping("/save")
	public CustomerSave createCustomerSave(@RequestBody CustomerSave saveCustomer) throws Exception{
		 
		System.out.println(saveCustomer);
		//Borrower save
		Customer customer=new Customer();
			customer.setCname(saveCustomer.getBname());
			customer.setFname(saveCustomer.getBfhname());
			customer.setMobnumber(saveCustomer.getBcontectnum());
			customer.setDob(saveCustomer.getBdob());
			customer.setEducation(saveCustomer.getBeducation());
			customer.setAddl1(saveCustomer.getBaddressl1());
			customer.setAddl2(saveCustomer.getBaddressl2());
			customer.setLandmark(saveCustomer.getBlandmark());
			customer.setCity(saveCustomer.getBcity());
			customer.setDistrict(saveCustomer.getBdistrict());
			customer.setState(saveCustomer.getBstate());
			customer.setPincode(saveCustomer.getBpincode());
			customer.setPannum(saveCustomer.getBpannum());
			customer.setKycname(saveCustomer.getBkycname());
			customer.setKycnum(saveCustomer.getBkycnumber());
			customer.setEntrydate(saveCustomer.getBentrydate());
			customer.setEntryby(saveCustomer.getBentryby());
			customer.setSourcedBy(saveCustomer.getSourcedby());
			//saveBerrower
			customer=customerRepository.save(customer);
			int borrowerId=customer.getCid();
			
			//Co_Borrower SaveC
			customer=new Customer();
			customer.setCname(saveCustomer.getCbname());
			customer.setFname(saveCustomer.getCbfhname());
			customer.setMobnumber(saveCustomer.getCbcontectnum());
			customer.setDob(saveCustomer.getCbdob());
			customer.setEducation(saveCustomer.getCbeducation());
			customer.setAddl1(saveCustomer.getCbaddressl1());
			customer.setAddl2(saveCustomer.getBaddressl2());
			customer.setLandmark(saveCustomer.getCblandmark());
			customer.setCity(saveCustomer.getCbcity());
			customer.setDistrict(saveCustomer.getCbdistrict());
			customer.setState(saveCustomer.getCbstate());
			customer.setPincode(saveCustomer.getCbpincode());
			customer.setPannum(saveCustomer.getCbpannum());
			customer.setKycname(saveCustomer.getCbkycname());
			customer.setKycnum(saveCustomer.getCbkycnumber());
			customer.setEntrydate(saveCustomer.getBentrydate());
			customer.setEntryby(saveCustomer.getBentryby());
			customer.setSourcedBy(saveCustomer.getSourcedby());
			//save Co_Borrower
			customerRepository.save(customer);
			int coborrowerId=customer.getCid();
			
			//Lead Generation
		  
			Lead lead= new Lead();
			lead.setBorrowerID(borrowerId);
			lead.setCoBorrowerId(coborrowerId);
			lead.setEntryBy(saveCustomer.getBentryby());
			lead.setAplicationDate(saveCustomer.getAppdate());
			lead.setBranchID(saveCustomer.getBranch());
			lead.setCenterID(saveCustomer.getCentername());
			lead.setSourcedBy(saveCustomer.getSourcedby());
			lead.setManageBy(saveCustomer.getSourcedby());
			
//															lead.s(coborrowerId)
			leadRepository.save(lead);
			
			
		return saveCustomer;
		
	}
	
	@GetMapping("/lead-data/{leadId}")
 
	public Map<String, Object> getLeadData(@PathVariable Integer leadId) {
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
	        Long centerId=lead.getCenterID();
	        List<Center> center=centerRepository.getCenterByncid(centerId);
	        result.put("lead", lead);
	        result.put("borrower", borrower);
	        result.put("coBorrower",coBorrower);
	        result.put("center", center);
	        result.put("loan", loan);
	        if (!center.isEmpty()) {
	            Center centerData  = center.get(0); // Assuming there is only one center
	            Long branchId = centerData.getBname();
	            Optional<Branch> branch = branchRepository.findById(branchId);
	            Integer timeId=centerData.getTime();
		        List<Time> time=timeRepository.getTimeBytid(timeId);
		        Integer dayId=centerData.getCmday();
		        List<Days> day=daysRepository.getDayBydid(dayId);
		        result.put("day", day);
		        result.put("time", time);
	            result.put("branch", branch);
	        }
	    }

	    
	    return result;
	}
}
