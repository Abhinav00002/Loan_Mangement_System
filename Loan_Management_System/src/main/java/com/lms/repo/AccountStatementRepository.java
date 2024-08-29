package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.AccountStatement;

@Repository
public interface AccountStatementRepository extends JpaRepository<AccountStatement, Long> {
	@Transactional
	@Procedure(name = "GetAccountStatement")
	List<Map<String, Object>> getAccountStatement(@Param("accountId") Integer accountId);

	@Query(value = "  select   id,    " + "      depositAmount,    " + "     withdrawalAmount,  "
			+ "      salaryAmount, " + "         remark, " + "     dcrDate,    " + "     depositDate,    "
			+ "     branchName,    " + "     accountId,    " + "     accountName,    " + "     accountBankId,    "
			+ "     accountBankName,    " + "     accountBankCity,    " + "     accountBankType,    "
			+ "     accountBankStatus from (" + " "
			+ " select  dm.id ,dm.amount as depositAmount, dm.remark AS remark, am.id as accountId, am.account_name AS accountName,dm.bank_id AS accountBankId,dm.bank_name AS accountBankName, am.bank_city AS accountBankCity,am.bank_type AS accountBankType,am.status AS accountBankStatus,dm.branch_name AS branchName, dm.deposit_date AS depositDate"
			+ " ,dm.dcr_date as dcrDate," + " 0 withdrawalAmount , 0 salaryAmount  from deposit_master dm "
			+ " inner join account_master am ON am.id =dm.account_id" + " "
			+ " where dm.account_id=:accountId and dm.clear_status!=1 " + " union all "
			+ " select  wm.id,0 depositAmount , wm.remark AS remark, am.id as accountId, am.account_name AS accountName,wm.bank_id AS accountBankId,wm.bank_name AS accountBankName,  am.bank_city AS accountBankCity,am.bank_type AS accountBankType,am.status AS accountBankStatus,wm.branch_name AS branchName, wm.deposit_date AS depositDate"
			+ " ,wm.dcr_date as dcrDate,  wm.amount AS withdrawalAmount , 0 salaryAmount  from withdrawal_master wm"
			+ "  inner join account_master am ON am.id =wm.account_id"
			+ "  where wm.account_id=:accountId and wm.clear_status!=1  " + "   union all "
			+ " select  sp.salary_id as id, 0 depositAmount, sp.remark AS remark, am.id as accountId, am.account_name AS accountName,am.bank_id AS accountBankId,am.bank_name AS accountBankName, am.bank_city AS accountBankCity,am.bank_type AS accountBankType,am.status AS accountBankStatus, sp.branch_name AS branchName,"
			+ " sp.salary_date as depositDate, sp.salary_date as dcrDate,"
			+ "  0 withdrawalAmount ,sp.amount as salaryAmount  from salary_paid sp"
			+ "  inner join account_master am ON am.id =sp.account" + "  where sp.account=:accountId "
			+ ")statement order by dcrDate  ", nativeQuery = true)
	List<Map<String, Object>> getAccountStatement1(@Param("accountId") Integer accountId);

	@Query(value = " select   id,    " + "	  		      depositAmount,    " + "	  		     withdrawalAmount, "
			+ "					  remark, " + "	  		      salaryAmount,  " + "	  		     dcrDate,    "
			+ "	  		     depositDate,    " + "	  		     branchName,    " + "	  		     accountId,    "
			+ "	  		     accountName,    " + "	  		     accountBankId,    "
			+ "	  		     accountBankName,    " + "	  		     accountBankCity,    "
			+ "	  		     accountBankType,    " + "	  		     accountBankStatus from (" + " "
			+ " select  dm.id ,dm.amount as depositAmount, dm.remark AS remark, am.id as accountId, am.account_name AS accountName,dm.bank_id AS accountBankId,dm.bank_name AS accountBankName, am.bank_city AS accountBankCity,am.bank_type AS accountBankType,am.status AS accountBankStatus,dm.branch_name AS branchName, dm.deposit_date AS depositDate"
			+ " ,dm.dcr_date as dcrDate," + " 0 withdrawalAmount , 0 salaryAmount  from deposit_master dm "
			+ " inner join account_master am ON am.id =dm.account_id" + " "
			+ " where dm.account_id=:accountId and dm.clear_status!=1 AND dm.dcr_date BETWEEN  :fromDate AND  :toDate"
			+ " union all "
			+ " select  wm.id,0 depositAmount, wm.remark AS remark , am.id as accountId, am.account_name AS accountName,wm.bank_id AS accountBankId,wm.bank_name AS accountBankName,  am.bank_city AS accountBankCity,am.bank_type AS accountBankType,am.status AS accountBankStatus,wm.branch_name AS branchName, wm.deposit_date AS depositDate"
			+ " ,wm.dcr_date as dcrDate,  wm.amount AS withdrawalAmount , 0 salaryAmount  from withdrawal_master wm"
			+ "  inner join account_master am ON am.id =wm.account_id"
			+ "  where wm.account_id=:accountId and wm.clear_status!=1 AND wm.dcr_date  BETWEEN   :fromDate AND  :toDate"
			+ "   union all "
			+ " select  sp.salary_id as id, 0 depositAmount, sp.remark AS remark, am.id as accountId, am.account_name AS accountName,am.bank_id AS accountBankId,am.bank_name AS accountBankName, am.bank_city AS accountBankCity,am.bank_type AS accountBankType,am.status AS accountBankStatus, sp.branch_name AS branchName,"
			+ " sp.salary_date as depositDate, sp.salary_date as dcrDate,"
			+ "  0 withdrawalAmount ,sp.amount as salaryAmount  from salary_paid sp"
			+ "  inner join account_master am ON am.id =sp.account"
			+ "  where sp.account=:accountId AND sp.salary_date  BETWEEN   :fromDate AND  :toDate"
			+ ")statement order by dcrDate ", nativeQuery = true)
	List<Map<String, Object>> getAccountStatementBetweentoDateAndfromDate(@Param("accountId") Integer accountId,
			@Param("toDate") LocalDate toDate, @Param("fromDate") LocalDate fromDate);

	@Query(value = "select openingDate.reportDate,  COALESCE(deposit, 0)-COALESCE(withdrawalAmount, 0)-COALESCE(salary, 0) As openingAmount  from (select :fromDate AS reportDate) openingDate "
			+ "left Join " + "(select   sum(dm.amount) AS deposit  , :fromDate AS reportDate from deposit_master dm   "
			+ " where dm.account_id=:accountId and dm.clear_status!=1 and dm.dcr_date < :fromDate " + "  "
			+ " ) deposit on deposit.reportDate=openingDate.reportDate " + "inner join ( "
			+ "select     sum(wm.amount) AS withdrawalAmount , :fromDate AS reportDate  from withdrawal_master wm "
			+ "  inner join account_master am ON am.id =wm.account_id "
			+ "  where wm.account_id=:accountId and wm.clear_status!=1  and wm.dcr_date < :fromDate "
			+ ") withdrow on withdrow.reportDate=openingDate.reportDate " + "inner join( "
			+ " select   sum(sp.amount) as salary, :fromDate AS reportDate  from salary_paid sp " + "  "
			+ "  where sp.account=:accountId  and sp.salary_date < :fromDate "
			+ "  ) salary  on salary.reportDate=openingDate.reportDate ", nativeQuery = true)
	public List<Map<String, Object>> getOpeningAmountfromDate(@Param("accountId") Integer accountId,
			@Param("fromDate") LocalDate fromDate);

}
