package com.lms.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;

@Repository
public interface LoanCreationRepository extends JpaRepository<LoanCreation, Long>{
	
	public List<LoanCreation> findAll();

	public List<LoanCreation> getLoanCreationByleadid(Integer leadid);

 

	public List<LoanCreation> findByddateBetween(LocalDate toDate, LocalDate fromDate);
}
