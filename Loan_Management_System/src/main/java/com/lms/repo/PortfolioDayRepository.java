package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.PortfolioDay;

@Repository
public interface PortfolioDayRepository extends JpaRepository<PortfolioDay, Integer> {
	
	
	@Query(value =   "SELECT    "
			+   "    pd.*,  "
			+   "    cb.customer_name AS borrowerName,  "
			+   "    cb.mobile_number AS contectNo,  "
			+   "    sm.emi AS sEmi,  "
			+   "    sm.loneamount AS financeAmount,  "
			+   "    cbs.customer_name AS coBorrowerName  "
			+   "FROM   "
			+   "    portfolio_day pd  "
			+   "INNER JOIN  "
			+   "    customer_master cb ON cb.customer_id = pd.borrowerid  "
			+   "INNER JOIN  "
			+   "    customer_master cbs ON cbs.customer_id = pd.co_borrower_id  "
			+   "INNER JOIN  "
			+   "    loan_master lm ON lm.loan_id = pd.loan_id  "
			+   "INNER JOIN  "
			+   "    scheme_master sm ON sm.scheme_id = lm.scheme   "
			, nativeQuery = true)
 	public List<Map<String, Object>> findAllData();
	
	
	//Find DPD
	@Query(value =   "SELECT  "
			+   "    pd.loan_id AS loanId,  "
			+   "    pd.due_date AS dueDate,  "
			+   "    pd.disbursement_date AS disDate,  "
			+   "    pd.openning_amount AS openingAmount,  "
			+   "    pd.emipending AS emiPending,  "
			+   "    pd.dpd AS dueDPD,  "
			+   "    pd.pendinginst AS pendingInstallment, "
			+   "    sm.tenor AS totalInstallment, "
			+   "    pd.branchid AS branchId,  "
			+   "    pd.leadid AS leadId,  "
			+   "    pd.centerid AS centerId,  "
			+   "    pd.borrowerid AS borrowerid,  "
			+   "    pd.co_borrower_id AS coBorrowerId,  "
			+   "    pd.manage_by AS manageBy,  "
			+   "    pd.entrydate AS entryDate,  "
			+   "    cb.customer_name AS borrowerName,  "
			+   "    cb.mobile_number AS contectNo,  "
			+   "    sm.emi AS sEmi,  "
			+   "    sm.loneamount AS financeAmount,  "
			+   "    cbs.customer_name AS coBorrowerName,  "
			+   "    bm.branch_name AS branchName  "
			+   "FROM  "
			+   "    portfolio_day pd  "
			+   "INNER JOIN  "
			+   "    customer_master cb ON cb.customer_id = pd.borrowerid  "
			+   "INNER JOIN  "
			+   "    customer_master cbs ON cbs.customer_id = pd.co_borrower_id  "
			+   "INNER JOIN  "
			+   "    loan_master lm ON lm.loan_id = pd.loan_id  "
			+   "INNER JOIN  "
			+   "    branch_master bm ON bm.branch_id = pd.branchid  "
			+   "INNER JOIN  "
			+   "    scheme_master sm ON sm.scheme_id = lm.scheme  "
			+   "WHERE  "
			+   "    pd.dpd > 0  "
			+   "ORDER BY  "
			+   "    pd.dpd   "
			, nativeQuery = true)
 	public List<Map<String, Object>> findAllDPDCase();
	
	
	//find DPD by center Id
	@Query(value =   "SELECT  "
			+   "    pd.loan_id AS loanId,  "
			+   "    pd.due_date AS dueDate,  "
			+   "    pd.disbursement_date AS disDate,  "
			+   "    pd.openning_amount AS openingAmount,  "
			+   "    pd.emipending AS emiPending,  "
			+   "    pd.dpd AS dueDPD,  "
			+   "    pd.pendinginst AS pendingInstallment, "
			+   "    sm.tenor AS totalInstallment,"
			+   "    pd.branchid AS branchId,  "
			+   "    pd.leadid AS leadId,  "
			+   "    pd.centerid AS centerId,  "
			+   "    pd.borrowerid AS borrowerid,  "
			+   "    pd.co_borrower_id AS coBorrowerId,  "
			+   "    pd.manage_by AS manageBy,  "
			+   "    pd.entrydate AS entryDate,  "
			+   "    cb.customer_name AS borrowerName,  "
			+   "    cb.mobile_number AS contectNo,  "
			+   "    sm.emi AS sEmi,  "
			+   "    sm.loneamount AS financeAmount,  "
			+   "    cbs.customer_name AS coBorrowerName,  "
			+   "    bm.branch_name AS branchName  "
			+   "FROM  "
			+   "    portfolio_day pd  "
			+   "INNER JOIN  "
			+   "    customer_master cb ON cb.customer_id = pd.borrowerid  "
			+   "INNER JOIN  "
			+   "    customer_master cbs ON cbs.customer_id = pd.co_borrower_id  "
			+   "INNER JOIN  "
			+   "    loan_master lm ON lm.loan_id = pd.loan_id  "
			+   "INNER JOIN  "
			+   "    branch_master bm ON bm.branch_id = pd.branchid  "
			+   "INNER JOIN  "
			+   "    scheme_master sm ON sm.scheme_id = lm.scheme  "
			+   "WHERE  "
			+   "    pd.centerid = :centerId  "
			+   "    AND pd.branchid = :branchId  "
			+   "    AND DPD > 0  "
			+   "ORDER BY  "
			+   "    pd.dpd   "
			+   "  ", nativeQuery = true)
	public List<Map<String, Object>> findAllDPDCaseByCenterId(@Param(  "centerId") Integer centerId, @Param(  "branchId") Integer branchId);

	
	
	
	@Procedure(value =   "auto_pro_portfolio_day",procedureName =   "auto_pro_portfolio_day")
	public void dailyRunPortfolio();
	
	@Procedure
	@Query(nativeQuery = true, value =   "call lms.proPortfolioDue(:dueDate)" )
	public void proPortfolioDue(@Param(  "dueDate") LocalDate dueDate);
	
	
	
}
