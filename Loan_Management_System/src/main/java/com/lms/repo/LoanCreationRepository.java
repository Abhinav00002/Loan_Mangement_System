package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;

@Repository
public interface LoanCreationRepository extends JpaRepository<LoanCreation, Long>{
	
	public List<LoanCreation> findAll();

	public List<LoanCreation> getLoanCreationByleadid(Integer leadid);
	
	@Query(value = "select lm.loan_id, lm.bussiness_expences, lm.branchname, lm.borower_co_borower_relation, lm.centername, lm.disbursement_date, lm.home_expences,"
			+ " lm.lead_id, lm.lone_expences, lm.name, lm.other_income, lm.purpose, lm.scheme, lm.selfincome, lm.spouse_income, lm.spouse_name, "
			+ " lm.sourcedby, lm.meeting_date, lm.meeting_day, lm.status, lm.caution from  loan_master lm where lm.branchname=:branchname",nativeQuery = true)
	public List<LoanCreation>  findByBranchname(Integer branchname);
 
	@Query(value = "SELECT lc.loan_id,lc.disbursement_date, lc.meeting_date, s.emi, s.pfamount, s.tenor,  s.loneamount,  l.leadid, c.customer_id as c_column1, c.customer_name as c_column2,"+
			"c.mobile_number as borrower_Mobile_No, cb.customer_id as cb_column1, cb.customer_name as cb_column2,"+ 
			"cb.mobile_number as coBorrower_Mobile_No,  ct.center_id, t.time, d.days_name, b.branch_name as b_name ,sm.staff_name AS username,sm.staff_id AS id "+
			"FROM loan_master lc " +
            "INNER JOIN scheme_master s ON lc.scheme = s.scheme_id " +
            "INNER JOIN lead_master l ON lc.lead_id = l.leadid " +
            "INNER JOIN branch_master b ON lc.branchname = b.branch_id "+
            "INNER JOIN customer_master c ON l.borrowerid = c.customer_id " +
            "INNER JOIN customer_master cb ON l.co_borrower_id = cb.customer_id " +
            "INNER JOIN center_master ct ON l.centerid = ct.center_id   and ct.branch_name =l.branchid  " +
            "INNER JOIN time t ON ct.center_meeting_time = t.time_id " +
            "INNER JOIN days d ON ct.center_meeting_day = d.days_id " +
            "INNER JOIN staff_master sm ON sm.staff_id = lc.sourcedby " +
            "WHERE lc.disbursement_date BETWEEN :toDate AND :fromDate", nativeQuery = true)
 
	public  List<Map<String, Object>> findByddateBetween(LocalDate toDate, LocalDate fromDate);

	
	@Query(value = "SELECT lc.loan_id,lc.disbursement_date, lc.meeting_date, s.emi, s.pfamount, s.tenor,  s.loneamount,  l.leadid, c.customer_id as c_column1, c.customer_name as c_column2,"+
			"c.mobile_number as borrower_Mobile_No, cb.customer_id as cb_column1, cb.customer_name as cb_column2,"+ 
			"cb.mobile_number as coBorrower_Mobile_No,  ct.center_id, t.time, d.days_name, b.branch_name as b_name ,sm.staff_name AS username,sm.staff_id AS id "+
			"FROM loan_master lc " +
            "INNER JOIN scheme_master s ON lc.scheme = s.scheme_id " +
            "INNER JOIN lead_master l ON lc.lead_id = l.leadid " +
            "INNER JOIN branch_master b ON lc.branchname = b.branch_id "+
            "INNER JOIN customer_master c ON l.borrowerid = c.customer_id " +
            "INNER JOIN customer_master cb ON l.co_borrower_id = cb.customer_id " +
            "INNER JOIN center_master ct ON l.centerid = ct.center_id   and ct.branch_name =l.branchid  " +
            "INNER JOIN time t ON ct.center_meeting_time = t.time_id " +
            "INNER JOIN days d ON ct.center_meeting_day = d.days_id " +
            "INNER JOIN staff_master sm ON sm.staff_id = lc.sourcedby " +
            "WHERE   lc.branchname = :branchId AND lc.disbursement_date BETWEEN :toDate AND :fromDate", nativeQuery = true)
 
	public  List<Map<String, Object>> findByBranchIdAndDdateBetween(Integer branchId,LocalDate toDate, LocalDate fromDate);

	@Query(value = "SELECT * from loan_master WHERE loan_id=:loanId", nativeQuery = true)
	public  LoanCreation getLoanByid(Integer loanId);
	
	
	 
	@Query(value = "SELECT * FROM loan_master WHERE loan_id = :loanId  ", nativeQuery = true)
	Optional<LoanCreation> findByIdanddisDate(@Param("loanId") Integer loanId);
	
	
	 
	 @Modifying 
	 @Transactional
	 @Query(value = "UPDATE LoanCreation lm "
	 		+ "SET lm.caution = 1 "
	 		+ "WHERE lm.id = :loanId ")
	 public void updatecautionToloanId( @Param("loanId")  Long loanId );
	

	 }
