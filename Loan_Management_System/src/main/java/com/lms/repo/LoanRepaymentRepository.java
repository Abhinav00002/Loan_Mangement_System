package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.LoanRepayment;


@Repository
public interface LoanRepaymentRepository  extends JpaRepository<LoanRepayment, Long>{

	
	
	 //get LoanRepayment By loan id
	@Query(value="SELECT  "
			+ "    lrm.*, "
			+ "    CASE "
			+ "        WHEN lrm.collection_date IS NOT NULL AND lrm.collection_by IS NOT NULL THEN lrm.collection_date "
			+ "        ELSE NULL "// -- or another default value 
			+ "    END AS ReciptDate, "
			+ "    CASE "
			+ "        WHEN lrm.collection_date IS NOT NULL AND lrm.collection_by IS NOT NULL THEN sf.staff_id "
			+ "        ELSE NULL  " //-- or another default value
			+ "    END AS ReciptById, "
			+ "    CASE "
			+ "        WHEN lrm.collection_date IS NOT NULL AND lrm.collection_by IS NOT NULL THEN sf.staff_name "
			+ "        ELSE NULL  "//-- or another default value
			+ "    END AS ReciptByName "
			+ "FROM loan_repayment_master as lrm "
			+ "LEFT JOIN staff_master sf ON sf.staff_id = lrm.collection_by "
			+ "WHERE lrm.loan_id IN "
			+ "(SELECT ld.leadid FROM lead_master as ld WHERE ld.leadid IN "
			+ "(SELECT lm.lead_id FROM loan_master as lm WHERE lm.loan_id =:loanid))"
			, nativeQuery = true)
	 public List<Map<String, Object>> getLoanRepaymentByloanId(@Param("loanid") Integer loanid);
	
	
	 List<LoanRepayment> findByDueDateAndBranchId(LocalDate dueDate, Integer branchId);

	 List<LoanRepayment> findByDueDateBetween(LocalDate toDate, LocalDate fromDate);
	 
	 
	 @Modifying
	 @Transactional
	 @Query(value = "UPDATE LoanRepayment lrm SET lrm.centerId =:newCenterId "
	 		+ "WHERE lrm.loanId IN "
	 		+ "(SELECT lm.leadID FROM Lead lm WHERE lm.leadID IN "
	 		+ "(SELECT l.leadid FROM LoanCreation l "
	 		+ "WHERE l.id =:loanId))")
	 public void updatecenterIdByloanId( @Param("loanId")  long loanId,@Param("newCenterId") int newCenterId);
	 
	 @Query(value = "SELECT  lc.loan_id AS loanId, lc.disbursement_date AS loanCreationDate, s.loneamount AS loanAmount,   "
	 		+ "b.branch_name AS branchName,b.branch_id AS branchId,  c.customer_name AS customerName,c.mobile_number AS contactNo,  "
	 		+ "c.addresss_line1 AS addressLine1,  c.address_line2 AS addressLine2, cb.customer_name AS spouseName, "
	 		+ "ct.center_id AS centerId, t.time AS centerTime, d.days_name AS centerDay, sm.staff_id AS staffId, "
	 		+ "sm.staff_name AS staffName "
	 		+ "FROM loan_master lc "
	 		+ "INNER JOIN scheme_master s ON lc.scheme = s.scheme_id "
	 		+ "INNER JOIN lead_master l ON lc.lead_id = l.leadid "
//	 		+ "INNER JOIN loan_repayment_master lrm ON l.leadid = lrm.loan_id "
	 		+ "INNER JOIN branch_master b ON lc.branchname = b.branch_id "
	 		+ "INNER JOIN customer_master c ON l.borrowerid = c.customer_id "
	 		+ "INNER JOIN customer_master cb ON l.co_borrower_id = cb.customer_id "
	 		+ "INNER JOIN center_master ct ON l.centerid = ct.center_id and l.branchid=ct.branch_name "
	 		+ "INNER JOIN time t ON ct.center_meeting_time = t.time_id "
	 		+ "INNER JOIN days d ON ct.center_meeting_day = d.days_id "
	 		+ "INNER JOIN staff_master sm ON lc.sourcedby = sm.staff_id "
	 		+ "WHERE lc.loan_id =  :loanid", nativeQuery = true)
	 List<Map<String, Object>> getCombinedDataByid(@Param("loanid") Integer loanid);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @Query(value = "SELECT *"
	 		+ " FROM loan_repayment_master  "
	 		+ "where branch_id=:branchId and collection_date=:collectionDate", nativeQuery = true)
	 List<LoanRepayment> getLoanRepaymentsBycollectionDateandbranchId(@Param("collectionDate") LocalDate collectionDate, @Param("branchId") Integer branchId);
	 
	 
	 
	 
	 
	 
	 
	 @Query(value = "SELECT lrm.emi, "
	 		+ "    lc.loan_id AS loanId, "
	 		+ "    borrower.customer_name AS borrowerName, "
	 		+ "    borrower.mobile_number AS borrowerContact, "
	 		+ "    borrower.addresss_line1 AS borrowerAdd1, "
	 		+ "    borrower.address_line2 AS borrowerAdd2, "
	 		+ "    coBorrower.customer_name AS CoBorrowerName, "
	 		+ "    c.ncid AS centerId, "
	 		+ "    b.branch_id AS branchId, b.branch_name AS branchName, "
	 		+ "    time.time AS Time, "
	 		+ "    days.days_name AS day, "
	 		+ "    le.leadid AS leadId "
	 		+ "FROM loan_repayment_master lrm "
	 		+ "JOIN loan_master lc ON lc.lead_id = lrm.loan_id "
	 		+ "JOIN lead_master le ON le.leadid = lrm.loan_id "
	 		+ "LEFT JOIN customer_master borrower ON borrower.customer_id = le.borrowerid "
	 		+ "LEFT JOIN customer_master coBorrower ON coBorrower.customer_id = le.co_borrower_id "
	 		+ "JOIN center_master c ON c.center_id = le.centerid "
	 		+ "LEFT JOIN branch_master b ON b.branch_id = :branchId "
	 		+ "LEFT JOIN time ON time.time_id = c.center_meeting_time "
	 		+ "LEFT JOIN days ON days.days_id = c.center_meeting_day "
	 		+ "WHERE lrm.due_date = :dueDate "
	 		+ "    AND lrm.branch_id = :branchId ", nativeQuery = true)
	 List<Map<String, Object>> findBydueDateAndBranchId(@Param("dueDate") LocalDate dueDate, @Param("branchId") Integer branchId);
	 
	 
	 
//	 select sum(amount) from deposit_master where branch_id=2 and entry_date='2023-08-07';// for sum of deposit
    //

}
