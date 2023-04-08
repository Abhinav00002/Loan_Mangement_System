package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.LoanRepayment;

public interface LoanRepaymentRepository  extends JpaRepository<LoanRepayment, Long>{

}
