package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.Account;

import net.bytebuddy.asm.Advice.Local;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findBybankId(Integer accountId);

	@Query(value = "SELECT" + "  sum(lrm.emi) as dueAmount," + "  sum(lrm.principle) as principle,"
			+ "  sum(lrm.intrest) AS interest," + "  sum(case when"
			+ "	lrm.status=2 and last_day(lrm.due_date) <= last_day(lrm.collection_date) then lrm.emi else 0"
			+ "    end) AS dueAginstCollection " + "FROM"
			+ "    loan_repayment_master lrm  inner join  loan_master lm on lm.status=2 and lrm.loan_id=lm.loan_id "
			+ "    where lrm.preclose=0 and lrm.due_date between :toDate and :fromDate", nativeQuery = true)
	public List<Map<String, Object>> getTotalDue(@Param("toDate") LocalDate toDate,
			@Param("fromDate") LocalDate fromDate);

//	@Query(value="SELECT"
//			+ "  sum(lrm.emi) as dueAmount,"
//			+ "  sum(lrm.principle) as principle,"
//			+ "  sum(lrm.intrest) AS interest,"
//			+ "  sum(case when"
//			+ "	lrm.status=2 and last_day(lrm.due_date) <= last_day(lrm.collection_date) then lrm.emi else 0"
//			+ "    end) AS dueAginstCollection "
//			+ "FROM"
//			+ "    loan_repayment_master lrm  inner join  loan_master lm on lm.status=2 and lrm.loan_id=lm.loan_id "
//			+ "    where lrm.preclose=0  ", nativeQuery = true)
//	public List<Map<String, Object>> getTotalDues();
//	

// get damp of all data
	@Query(value = "SELECT"
			+ "    lm.*, scm.*, lem.borrowerid AS borrowerid, cum.customer_name AS coBorrowerName, lem.co_borrower_id , cm.customer_name AS borrowerName, cm.date_of_birth AS borrowerDOB, cm.mobile_number AS borrowerContectNumber,"
			+ "    cm.kyc_number AS borrrowerKYC, cm.pan_number AS borowerPAN,cm.addresss_line1 AS borrowerAddl1,cm.address_line2 AS borrowerAddl2"
			+ "    ,dm.district_name AS borrowerDistrict, sm.state_name AS borowerState, bm.branch_name AS branchName , scm.loneamount as financeAmount "
			+ "FROM" + "    loan_repayment_master lrm "
			+ "    INNER JOIN loan_master lm ON lm.status = 2 AND lrm.loan_id = lm.loan_id "
			+ "    inner join lead_master lem on lem.leadid=lm.lead_id"
			+ "    inner join customer_master cm ON cm.customer_id=lem.borrowerid"
			+ "    inner join customer_master cum ON cum.customer_id=lem.co_borrower_id"
			+ "    inner join district_master dm On cm.district=dm.district_id"
			+ "    Inner Join state_master sm On cm.state=sm.state_id"
			+ "    inner join branch_master bm On lm.branchname = bm.branch_id  "
			+ "    inner join scheme_master scm ON lm.scheme=scm.scheme_id " + "WHERE" + "    lrm.preclose = 0"
			+ "     and lrm.due_date between :toDate and :fromDate "
	/*
	 * + "GROUP BY" + "    lm.loan_id"
	 */, nativeQuery = true)
	public List<Map<String, Object>> getTotalDamp(@Param("toDate") LocalDate toDate,
			@Param("fromDate") LocalDate fromDate);

}
