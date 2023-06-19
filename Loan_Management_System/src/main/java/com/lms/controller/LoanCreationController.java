package com.lms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Center;
import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;
import com.lms.model.Scheme;
import com.lms.repo.CenterRepository;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.LoanRepaymentRepository;
import com.lms.repo.SchemeRepository; 



@RestController
@CrossOrigin("*")
@RequestMapping("/loanCreation")
public class LoanCreationController {

	
	@Autowired
	private LoanCreationRepository loanCreationRepository;
	@Autowired
	private SchemeRepository schemeRepository;
	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
	@Autowired
	private CenterRepository centerRepository;
	
	
	
	@PostMapping("/")
	public LoanCreation createLoanCreation(@RequestBody LoanCreation loanCreation) throws Exception{
		Integer leadId=loanCreation.getLeadid();
		Integer	centerId=loanCreation.getCentername();
		Integer	branchId=loanCreation.getBranchname();
	 System.out.println("lead id ===== "+leadId);
		double financeamount=0;
		long schemeno =loanCreation.getScheme();
		//fetch Scheme By id
		Scheme scheme=schemeRepository.findById(schemeno); 
		double loanAmount=	scheme.getLoneamount();
		double interestRate=scheme.getIntrestrate();  
		int emitype = scheme.getEmitype();
		double emi=scheme.getEmi();
		double tenor=scheme.getTenor();
		double pfAmount=scheme.getPfamount();
		double insuranceAmount=scheme.getInsuranceamount();
		long schemeBy= scheme.getSchemeBy();
		double irr=scheme.getIrr();
	 
		
		
		
		
		LocalDate dueDate=scheme.getSchemeDate();
		System.out.println(dueDate);
		// get center date  
		 
		 
		
	 // Calculate interest for payment
        double  Interest = 0;
        double remainingAmount = loanAmount;
        List<LoanRepayment> liLoanRepayments=new ArrayList<LoanRepayment>();
        
        /*for loop started for interest calculation*/
        
        for (int i = 1; i <= tenor; i++) {
     	
       
        	
        			/*Four Nightly*/
			        if (emitype==3) {
			        		// Interest=(remainingAmount*interestRate/100*365)*7; 
			        		 dueDate=dueDate.plusDays(14);
			        }  /*Weekly EMI*/ 
			        else if (emitype==2) {
			        	// Interest=(remainingAmount*interestRate/100*365)*14;
			        	 dueDate=dueDate.plusDays(7);
			        }/* Monthly EMI */
			        else if (emitype==1) {
//			        	 Interest=(remainingAmount*interestRate/100*12);//monthly
			        	 dueDate=dueDate.plusMonths(1);
			        }
			        Interest=remainingAmount*irr;
			        double principal = emi - Interest ;
       
          
           
           
            
            
            LoanRepayment loanRepayment=new LoanRepayment(); 
            loanRepayment.setLoanId(leadId);
            loanRepayment.setIntrest(Interest);
            loanRepayment.setEmi(emi);
            loanRepayment.setCollectionBy(schemeBy);
            loanRepayment.setPrinciple(principal);
            loanRepayment.setInstallmentNo(i);
//            loanRepayment.setCollectionDate(dueDate);
            loanRepayment.setDueDate(dueDate); 
            loanRepayment.setStatus(1);
            loanRepayment.setOpenningAmount(remainingAmount);
            loanRepayment.setBranchId(branchId);
            loanRepayment.setCenterId(centerId);
            liLoanRepayments.add(loanRepayment);
            
            remainingAmount =remainingAmount - principal;
        } /*for loop close*/ 
            
        
        loanRepaymentRepository.saveAll(liLoanRepayments);
     
      
       

		
		LoanCreation loan=loanCreationRepository.save(loanCreation);
		
		System.out.println(loan);
		return loan;
		
	
        }
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/list")
	public List<LoanCreation> getLoanCreations(Integer schemeno){
		List<LoanCreation> loanCreations=new ArrayList<LoanCreation>();
		loanCreations=loanCreationRepository.findAll();
		return loanCreations;
	}
	
	
	@GetMapping("/list/passbook/{loan_Id}")
	public List<LoanRepayment> getLoanRepaymentByloanId(@PathVariable ("loan_Id") Integer loan_Id){
		return loanRepaymentRepository.getLoanRepaymentByloanId(loan_Id);
	}
	
	
	@GetMapping("/list/loanrepayment")
	public List<LoanRepayment> findByDueDateAndBranchId(@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
	                                                    @RequestParam("branchId") Integer branchId) {
		System.out.println("Due Date: " + dueDate);
	    System.out.println("Branch ID: " + branchId);
	    return loanRepaymentRepository.findByDueDateAndBranchId(dueDate, branchId);
	}

}
//si=(p*i*t/100)*365 
//simple intrest in installment for months = [P+ (P* n* r)/ 12* 100].