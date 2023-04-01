package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>{

	
//	public List<Lead> findAll();
	@Query(value="SELECT * FROM lead_master where leadid not in (select lead_id from loan_master where lead_id is not null) ",nativeQuery=true)
	 public List<Lead> findAll();
}
