package com.lms.repo;

import java.util.List;
import java.util.Map;

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
	@Query(value="SELECT lm.leadid AS leadID,lm.centerid AS centerID, lm.aplication_date AS aplicationDate, cm.customer_name AS borrowerID, "
			+ "cbm.customer_name AS coBorrowerId, bm.branch_name AS branchName, lm.branchid AS branchID, sm.staff_name AS entryBy, "
			+ "stm.staff_name AS manageBy, stam.staff_name AS sourcedBy  FROM lead_master lm  "
			+ "INNER JOIN customer_master cm ON cm.customer_id=lm.borrowerid "
			+ "INNER JOIN customer_master cbm ON cbm.customer_id=lm.co_borrower_id "
			+ "INNER JOIN branch_master bm ON bm.branch_id=lm.branchid "
			+ "INNER JOIN staff_master sm ON sm.staff_id=lm.entry_by "
			+ "INNER JOIN staff_master stm ON stm.staff_id=lm.manage_by "
			+ "INNER JOIN staff_master stam ON stam.staff_id=lm.sourced_by "
			+ "where lm.leadid not in (select lom.lead_id from loan_master lom where lom.lead_id is not null) ",nativeQuery=true)
	 public List<Map<String, Object>> findlead();
	
	public List<Lead> findByleadID(Integer leadID);
	
	
	 @Modifying
	    @Transactional
	    @Query( "UPDATE Lead l SET l.centerID = :newCenterId WHERE l.leadID IN (SELECT lm.leadid FROM LoanCreation lm WHERE lm.id = :leadID)"  )
	 void updatecenterIDByLeadID(@Param("leadID") long leadID, @Param("newCenterId") Long newCenterId);

	 
	  @Modifying
	  @Transactional
	  @Query( "UPDATE  Lead lm SET lm.manageBy = :ceManageBy WHERE lm.branchID = :bname AND lm.centerID= :centerId" )
	  void updateManageBy(@Param("ceManageBy") String ceManageBy, @Param("centerId") Long centerId, @Param("bname") Long bname);
	  @Modifying
	    @Transactional
	    @Query( "UPDATE Lead l SET l.manageBy = :ceManageBy WHERE l.leadID = :leadId AND l.branchID = :bname AND l.centerID= :centerId " )
	 void updateManageBy1(@Param("ceManageBy") String ceManageBy, @Param("centerId") Long centerId, @Param("bname") Long bname, @Param("leadId") int leadId);

 }
