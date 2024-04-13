package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.model.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
	
	@Query(value = " SELECT im.id, im.lender_id, im.amount, im.investment_type, im.investment_date, im.interest_rate, im.interest_start_date, im.status, im.close_date, im.entry_by, im.close_by, lm.lender_name "
			+ "      FROM investment_master im "
			+ "      INNER JOIN lender_master lm ON lm.lender_id=im.lender_id", nativeQuery = true)
	public  List<Investment> getAllInvestments();
	
	@Query(value = "SELECT  "
			+ "    SUM(lrm.intrest) AS Interest, "
			+ "    SUM(lrm.principle) AS Principle, "
			+ "    SUM(CASE WHEN lrm.preclose = 0 AND lrm.due_date BETWEEN :toDate AND :fromDate THEN sm.pfamount ELSE 0 END) AS total_pf, "
			+ "    (SUM(lrm.intrest) + SUM(lrm.principle)) AS total_loan_repayment, "
			+ "    (SUM(lrm.intrest) + SUM(lrm.principle) + SUM(CASE WHEN lrm.preclose = 0 AND lrm.due_date BETWEEN :toDate AND :fromDate THEN sm.pfamount ELSE 0 END) -  "
			+ "    (SELECT SUM(sp.amount) FROM salary_paid sp WHERE sp.salary_date BETWEEN :toDate AND :fromDate) -  "
			+ "    (SELECT SUM(dm.amount) FROM deposit_master dm WHERE dm.bank_id = 5 AND dm.deposit_date BETWEEN :toDate AND :fromDate)) AS total "
			+ "FROM  "
			+ "    loan_repayment_master lrm "
			+ "INNER JOIN  "
			+ "    loan_master lm ON lm.loan_id = lrm.loan_id "
			+ "INNER JOIN  "
			+ "    scheme_master sm ON sm.scheme_id = lm.scheme "
			+ "WHERE  "
			+ "    lrm.preclose = 0  "
			+ "    AND lrm.due_date BETWEEN :toDate AND :fromDate",nativeQuery = true)
	public List<Map<String, Object>> getSumOfInterestAndPF(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

	@Query(value = "SELECT  "
			+ "    SUM(lrm.intrest) AS Interest, "
			+ "    SUM(lrm.principle) AS Principle, "
			+ "    SUM(CASE WHEN lrm.preclose = 0 AND lrm.due_date BETWEEN :toDate AND :fromDate THEN sm.pfamount ELSE 0 END) AS total_pf, "
			+ "    (SUM(lrm.intrest) + SUM(lrm.principle)) AS total_loan_repayment, "
			+ "    (SUM(lrm.intrest) + SUM(lrm.principle) + SUM(CASE WHEN lrm.preclose = 0 AND lrm.due_date BETWEEN :toDate AND :fromDate THEN sm.pfamount ELSE 0 END) -  "
			+ "    (SELECT SUM(sp.amount) FROM salary_paid sp WHERE sp.salary_date BETWEEN :toDate AND :fromDate) -  "
			+ "    (SELECT SUM(dm.amount) FROM deposit_master dm WHERE dm.bank_id = 5 AND dm.deposit_date BETWEEN :toDate AND :fromDate)) AS total "
			+ "FROM  "
			+ "    loan_repayment_master lrm "
			+ "INNER JOIN  "
			+ "    loan_master lm ON lm.loan_id = lrm.loan_id "
			+ "INNER JOIN  "
			+ "    scheme_master sm ON sm.scheme_id = lm.scheme "
			+ "WHERE  "
			+ "    lrm.preclose = 0  "
			+ "    AND lrm.due_date BETWEEN :toDate AND :fromDate AND lrm.branch_id=:branchId",nativeQuery = true)
	public List<Map<String, Object>> getSumOfInterestAndPFByBranch(@Param("branchId") Integer branchId,@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

}
