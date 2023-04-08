package com.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;
import com.lms.model.Scheme;
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
	
	
	
	@PostMapping("/")
	public LoanCreation createLoanCreation(@RequestBody LoanCreation loanCreation) throws Exception{
		
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
		
		
	 // Calculate interest for payment
        double  Interest = 0;
        double remainingAmount = loanAmount;
        List<LoanRepayment> liLoanRepayments=new ArrayList<LoanRepayment>();
        
        /*for loop started for interest calculation*/
        for (int i = 1; i <= tenor; i++) {
     	
       
        	
        			/*Four Nightly*/
			        if (emitype==3) {
			        		 Interest=(remainingAmount*interestRate/100*365)*7;        	 
			        }  /*Weekly EMI*/ 
			        else if (emitype==2) {
			        	 Interest=(remainingAmount*interestRate/100*365)*14;
			        }/* Monthly EMI */
			        else if (emitype==1) {
			        	 Interest=(remainingAmount*interestRate/100*12);//monthly
			        }
			            double principal = emi - Interest ;
       
          
            remainingAmount =remainingAmount - principal;
           
            
            
            LoanRepayment loanRepayment=new LoanRepayment(); 
            loanRepayment.setLoanId(schemeno);
            loanRepayment.setIntrest(Interest);
            loanRepayment.setEmi(emi);
            loanRepayment.setCollectionBy(schemeBy);
            loanRepayment.setPrinciple(principal);
            loanRepayment.setInstallmentNo(i);
            loanRepayment.setCollectionDate(null);
            loanRepayment.setDueDate(null); 
            loanRepayment.getStatus();
            loanRepayment.setOpenningAmount(remainingAmount);
            liLoanRepayments.add(loanRepayment);
        } /*for loop close*/ 
            
        
        loanRepaymentRepository.saveAll(liLoanRepayments);
     
      
       

		
		LoanCreation loan=loanCreationRepository.save(loanCreation);
		
		System.out.println(loan);
		return loan;
		
	
        }
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/list")
	public List<LoanCreation> getLoanCreations(int schemeno){
		List<LoanCreation> loanCreations=new ArrayList<LoanCreation>();
		loanCreations=loanCreationRepository.findAll();
		return loanCreations;
	}
}
//si=(p*i*t/100)*365 
//simple intrest in installment for months = [P+ (P* n* r)/ 12* 100].