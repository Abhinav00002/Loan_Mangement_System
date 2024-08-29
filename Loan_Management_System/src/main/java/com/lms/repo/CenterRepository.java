package com.lms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.Center;
import com.lms.model.address.District;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

	
	//get all center
	public List<Center> findAll();
	//get Center by Id
	public List<Center> getCenterByncid(Long centerID) ;
	
	
	 @Query(value = "SELECT * FROM center_master where branch_name=:branch_name ORDER BY center_id DESC", nativeQuery = true)
	  public List<Map<String,  Object>> getCenterByBname(@Param("branch_name")  Long branch_name) ;
	  
	  @Query(value="Select max(center_id) centerId from center_master where branch_name=?",nativeQuery=true)
	public Integer getmaxcenterId(Long branchId);
	  
	  @Transactional
	  @Query(value = "SELECT cm.addressl1, cm.addressl2, cm.landmark, cm.pincode, cm.center_type, t.time, d.days_name , cm.ce_manage_by, sm.staff_name  "
	  		+ ",cm.center_id AS centerId, lm.manage_by, sm1.staff_name AS loanManageBy FROM center_master cm   "
	  		+ "JOIN lead_master lm ON cm.ncid = lm.centerid   "
	  		+ "JOIN loan_master lom ON lm.leadid = lom.lead_id   "
	  		+ "JOIN time t ON t.time_id = cm.center_meeting_time   "
	  		+ "JOIN days d ON d.days_id = cm.center_meeting_day   "
	  		+ "JOIN staff_master sm ON sm.staff_id=cm.ce_manage_by  "
	  		+ "JOIN staff_master sm1 ON sm1.staff_id=lm.manage_by "
	  		+ "WHERE lom.loan_id =  :loanId ORDER BY center_id DESC", nativeQuery = true)
	List<Map<String, Object>> getCenterDataByLoanId(@Param("loanId") Integer loanId);
	  
	  @Query(value = "SELECT "
	  		+ "    cm.center_id, "
	  		+ "    bm.branch_id, "
	  		+ "    bm.branch_name, "
	  		+ "    da.days_name, "
	  		+ "    COALESCE(sm.staff_name, 'Unknown') AS ce_manage_by, "
	  		+ "    tm.time AS centerTime, "
	  		+ "    cm.center_type, "
	  		+ "    COUNT(dp.loan_id) AS client "
	  		+ "FROM "
	  		+ "    center_master cm "
	  		+ "INNER JOIN "
	  		+ "    portfolio_day dp ON dp.branchid = cm.branch_name AND cm.center_id = dp.centerid "
	  		+ "INNER JOIN "
	  		+ "    branch_master bm ON bm.branch_id = cm.branch_name "
	  		+ "INNER JOIN "
	  		+ "    time tm ON tm.time_id = cm.center_meeting_time "
	  		+ "INNER JOIN "
	  		+ "    days da ON da.days_id = cm.center_meeting_day "
	  		+ "LEFT JOIN "
	  		+ "    staff_master sm ON cm.ce_manage_by = sm.staff_id "
	  		+ "WHERE "
	  		+ "    cm.branch_name = :branchId "
	  		+ "GROUP BY "
	  		+ "    cm.center_id, bm.branch_id, bm.branch_name, da.days_name, sm.staff_name, tm.time, cm.center_type ORDER BY cm.center_id DESC", nativeQuery = true)
	  List<Map<String, Object>>  getLiveCenterBybranchId(@Param("branchId") Integer branchId);
	  
	  
	  
	  
	  
	  @Query(value = "SELECT * FROM center_master WHERE center_id=:centerid ORDER BY center_id DESC", nativeQuery = true)
	  List<Map<String, Object>> getCenterBycenterId(@Param("centerid") Integer centerid);
	  
	  
	  
	  
	  @Modifying
	  @Transactional
	  @Query("UPDATE Center c SET c.ceManageBy = :ceManageBy WHERE c.bname = :bname AND c.centerId = :centerId")
	  void updatecenterManageBy(@Param("ceManageBy") Integer ceManageBy, @Param("centerId") Integer centerId, @Param("bname") Long bname);

	  @Modifying
	  @Transactional
	  @Query("UPDATE Center c SET c.time = :time WHERE c.bname = :bname AND c.centerId = :centerId")
	  void updatecenterTime(@Param("time") Integer time, @Param("centerId") Integer centerId, @Param("bname") Long bname);


		//get Client Details For Live center
		@Query(value = "SELECT"
				+ "    pd.loan_id AS loanId,"
				+ "    pd.due_date AS dueDate,"
				+ "    pd.disbursement_date AS disDate,"
				+ "    pd.openning_amount AS openingAmount,"
				+ "    pd.emipending AS emiPending,"
				+ "    pd.dpd AS dueDPD,"
				+ "    pd.pendinginst AS pendingInstallment, "
				+ "    sm.tenor AS totalInstallment,"
				+ "    pd.branchid AS branchId,"
				+ "    pd.leadid AS leadId,"
				+ "    pd.centerid AS centerId,"
				+ "    pd.borrowerid AS borrowerid,"
				+ "    pd.co_borrower_id AS coBorrowerId,"
				+ "    pd.manage_by AS manageBy,"
				+ "    COALESCE(stm.staff_name, 'Unknown') AS manageByName,"
				+ "    pd.entrydate AS entryDate,"
				+ "    cb.customer_name AS borrowerName,"
				+ "    cb.mobile_number AS contectNo,"
				+ "    sm.emi AS sEmi,"
				+ "    sm.loneamount AS financeAmount,"
				+ "    cbs.customer_name AS coBorrowerName,"
				+ "    bm.branch_name AS branchName "
				+ " FROM portfolio_day pd"
				+ " INNER JOIN customer_master cb ON cb.customer_id = pd.borrowerid"
				+ " INNER JOIN customer_master cbs ON cbs.customer_id = pd.co_borrower_id"
				+ " INNER JOIN loan_master lm ON lm.loan_id = pd.loan_id"
				+ " INNER JOIN branch_master bm ON bm.branch_id = pd.branchid"
				+ " INNER JOIN scheme_master sm ON sm.scheme_id = lm.scheme"
				+ "  left join staff_master stm ON pd.manage_by=stm.staff_id"
				+ " INNER JOIN center_master cm on cm.branch_name=pd.branchid and cm.center_id=pd.centerid and cm.center_type=:centerType"
				+ " WHERE"
				+ "    pd.branchid = :branchId"
				+ "    AND pd.centerid = :centerId  ",nativeQuery = true)
		List<Map<String, Object>>  getCenterClientDetails(@Param("centerType") Integer centerType, @Param("centerId") Integer centerId, @Param("branchId") Integer branchId );
		

		
		
		
		//get center by branchID and Center Type
		@Query(value = " SELECT cm.ncid, cm.addressl1, cm.addressl2, cm.branch_name  , bm.branch_name AS branchName,cm.city,  "
				+ " cm.center_meeting_day  ,d.days_name AS centerMeetingDay, cm.landmark, cm.pincode, cm.center_sourced_by,  COALESCE(stm.staff_name, 'Unknown') As sourcedBy, cm.state,  "
				+ " cm.center_meeting_time, t.time, cm.center_id, cm.ce_manage_by  ,   COALESCE(sm.staff_name, 'Unknown') As manageBy, cm.center_type  "
				+ " FROM center_master  cm  "
				+ "Inner Join branch_master bm ON  bm.branch_id=cm.branch_name "
				+ "Left Join days d ON d.days_id=cm.center_meeting_day "
				+ "Left Join staff_master sm ON sm.staff_id=cm.ce_manage_by "
				+ "inner Join staff_master stm ON stm.staff_id=cm.center_sourced_by  "
				+ "inner join time t on t.time_id=cm.center_meeting_time"
				+ " WHERE cm.branch_name = :branchId AND cm.center_type = :centerType",nativeQuery = true)
		public List<Map<String, Object>> centerBybranchIdAndcenterId(@Param("branchId") Integer branchId, @Param("centerType") Integer centerType);
		
//		@Query(value = "select  cm.ncid, cm.addressl1, cm.addressl2, cm.branch_name, cm.city, cm.center_meeting_day, cm.landmark, cm.pincode, "
//				+ "cm.center_sourced_by, cm.state, cm.center_meeting_time, cm.center_id, cm.ce_manage_by, cm.center_type "
//				+ " from center_master cm where cm.branch_name=:bname and cm.center_type=:centerType  "
//				,nativeQuery = true)
//	 public List<Center>  getCenterByTypeAndBranchId(@Param("centerType") Integer centerType, @Param("bname") Long bname );
}
