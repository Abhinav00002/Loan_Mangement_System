package com.lms.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

 
import com.lms.model.LoanRepayment;

public interface LoanRepaymentRepository  extends JpaRepository<LoanRepayment, Long>{

	
	
	 //get LoanRepayment By id
	 public List<LoanRepayment> getLoanRepaymentByloanId(Integer loan_Id);
	 List<LoanRepayment> findByDueDateAndBranchId(LocalDate dueDate, Integer branchId);

}
