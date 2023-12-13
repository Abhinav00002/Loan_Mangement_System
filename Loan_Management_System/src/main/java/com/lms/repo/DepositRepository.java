package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer>{
	
	
	
	
	@Query(value = "select "
			+ " * "
			+ " from deposit_master "
			+ " where deposit_date>=:fromDate AND deposit_date<=:toDate and clear_status!=1 ",
			nativeQuery = true)
	  List<Map<String, Object>> getDepositBydepositDate(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
	
	
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
			+ " INNER JOIN center_master cm ON cm.center_id = pd.centerid"
			+ " WHERE"
			+ "    cm.branch_name = :branchId"
			+ "    AND cm.center_type = :centerType"
			+ "    AND cm.center_id = :centerId",nativeQuery = true)
	List<Map<String, Object>>  getCenterClientDetails(@Param("centerType") Integer centerType, @Param("centerId") Integer centerId, @Param("branchId") Integer branchId );
	
	
	

}
