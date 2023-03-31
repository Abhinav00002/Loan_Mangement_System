package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.LoanCreation;

@Repository
public interface LoanCreationRepository extends JpaRepository<LoanCreation, Long>{
	
	public List<LoanCreation> findAll();

}
