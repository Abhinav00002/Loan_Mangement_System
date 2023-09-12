package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>{

	
//	public List<Lead> findAll();
	@Query(value="SELECT * FROM lead_master where leadid not in (select lead_id from loan_master where lead_id is not null) ",nativeQuery=true)
	 public List<Lead> findAll();
	
	public List<Lead> findByleadID(Integer leadID);
	
	
	 @Modifying
	    @Transactional
	    @Query("UPDATE Lead l SET l.centerID = :newCenterId WHERE l.leadID IN (SELECT lm.leadid FROM LoanCreation lm WHERE lm.id = :leadID)")
	 void updatecenterIDByLeadID(@Param("leadID") long leadID, @Param("newCenterId") Long newCenterId);

	
 }
