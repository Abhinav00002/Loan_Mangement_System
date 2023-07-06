package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.RenewalLoan;

public interface RenewalLoanRepository extends JpaRepository<RenewalLoan, Integer > {

	boolean existsByloanID(Integer leadId);
 
}
