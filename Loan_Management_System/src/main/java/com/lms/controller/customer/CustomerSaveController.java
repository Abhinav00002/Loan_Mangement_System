package com.lms.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Customer;
import com.lms.model.CustomerSave;
import com.lms.model.Lead;
import com.lms.repo.LeadRepository;
import com.lms.repo.customer.CustomerRepository; 
@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerSaveController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LeadRepository leadRepository;
	
	 
	
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
			lead.setSourcedBy(saveCustomer.getBentryby());
			//lead.setManageBy()
			//lead.set
			leadRepository.save(lead);
			
			
		return saveCustomer;
		
	}
}
