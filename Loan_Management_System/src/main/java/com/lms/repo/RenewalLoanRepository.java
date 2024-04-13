package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.model.RenewalLoan;

public interface RenewalLoanRepository extends JpaRepository<RenewalLoan, Integer > {

	boolean existsByloanID(Integer leadId);
 
	@Query(value = "SELECT lm.caution FROM  loan_master lm where lm.loan_id=:loanId", nativeQuery = true)
	Integer  IsLoancoutions(@Param("loanId") Integer loanId);
}
