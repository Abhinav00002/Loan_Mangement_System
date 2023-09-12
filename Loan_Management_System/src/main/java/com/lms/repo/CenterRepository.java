package com.lms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	
	 @Query(value = "SELECT * FROM center_master where branch_name=:branch_name", nativeQuery = true)
	  public List<Map<String,  Object>> getCenterByBname(@Param("branch_name")  Long branch_name) ;
	  
	  @Query(value="Select max(center_id) centerId from center_master where branch_name=?",nativeQuery=true)
	public Integer getmaxcenterId(Long branchId);
	  
	  @Transactional
	  @Query(value = "SELECT cm.addressl1, cm.addressl2, t.time, d.days_name "
	             + "FROM center_master cm "
	             + "JOIN lead_master lm ON cm.ncid = lm.centerid "
	             + "JOIN loan_master lom ON lm.leadid = lom.lead_id "
	             + "JOIN time t ON t.time_id = cm.center_meeting_time "
	             + "JOIN days d ON d.days_id = cm.center_meeting_day "
	             + "WHERE lom.loan_id = :loanId", nativeQuery = true)
	List<Map<String, Object>> getCenterDataByLoanId(@Param("loanId") Integer loanId);
	  
	  @Query(value = "select cm.center_id,bm.branch_id,bm.branch_name,da.days_name,cm.ce_manage_by, tm.time as centerTime, "
	  		+ "cm.center_type,count(dp.loan_id) as client  from center_master cm "
	  		+ "inner join portfolio_day dp  on dp.branchid=cm.branch_name and cm.center_id=dp.centerid "
	  		+ "inner join branch_master bm on bm.branch_id=cm.branch_name "
	  		+ "Inner join time tm on tm.time_id=cm.center_meeting_time "
	  		+ "inner join days da on da.days_id=cm.center_meeting_day    "
	  		+ "where cm.branch_name=:branchId group by cm.center_id,bm.branch_name,da.days_name,cm.ce_manage_by,"
	  		+ "cm.center_type,bm.branch_id,tm.time", nativeQuery = true)
	  List<Map<String, Object>>  getLiveCenterBybranchId(@Param("branchId") Integer branchId);
	  
	  
	  
	  
	  
	  @Query(value = "SELECT * FROM center_master WHERE center_id=:centerid", nativeQuery = true)
	  List<Map<String, Object>> getCenterBycenterId(@Param("centerid") Integer centerid);

}
