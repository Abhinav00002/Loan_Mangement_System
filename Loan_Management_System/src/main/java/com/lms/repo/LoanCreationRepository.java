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

 
	@Query(value = "SELECT lc.*, s.*, l.*, c.customer_id as c_column1, c.customer_name as c_column2,  c.mobile_number as borrower_Mobile_No, cb.customer_id as cb_column1, cb.customer_name as cb_column2,cb.mobile_number as coBorrower_Mobile_No,  ct.*, t.*, d.*, u.*, b.branch_name as b_name " +
            "FROM loan_master lc " +
            "INNER JOIN scheme_master s ON lc.scheme = s.scheme_id " +
            "INNER JOIN lead_master l ON lc.lead_id = l.leadid " +
            "INNER JOIN branch_master b ON lc.branchname = b.branch_id "+
            "INNER JOIN customer_master c ON l.borrowerid = c.customer_id " +
            "INNER JOIN customer_master cb ON l.co_borrower_id = cb.customer_id " +
            "INNER JOIN center_master ct ON l.centerid = ct.ncid " +
            "INNER JOIN time t ON ct.center_meeting_time = t.time_id " +
            "INNER JOIN days d ON ct.center_meeting_day = d.days_id " +
            "INNER JOIN user u ON lc.sourcedby = u.id " +
            "WHERE lc.disbursement_date BETWEEN :toDate AND :fromDate", nativeQuery = true)
 
	public  List<Map<String, Object>> findByddateBetween(LocalDate toDate, LocalDate fromDate);
	
	@Query(value = "SELECT * from loan_master WHERE loan_id=:loanId", nativeQuery = true)
	public  LoanCreation getLoanByid(Integer loanId);
	
	
	 
	@Query(value = "SELECT * FROM loan_master WHERE loan_id = :loanId  ", nativeQuery = true)
	Optional<LoanCreation> findByIdanddisDate(@Param("loanId") Integer loanId);


}
