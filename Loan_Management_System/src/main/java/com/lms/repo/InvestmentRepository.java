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
			+ "    CONCAT( "
			+ "        DATE_FORMAT(lrm.collection_date, '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(lrm.collection_date, '%Y') "
			+ "    ) AS Month_Year, "
			+ "    CAST(SUM(lrm.intrest) AS UNSIGNED) AS Interest,  "
			+ "    CAST(SUM(lrm.principle) AS UNSIGNED) AS Principle  "
			+ "FROM  "
			+ "    loan_repayment_master lrm "
			+ "WHERE  "
			+ "    lrm.preclose = 0  "
			+ "    AND lrm.collection_date BETWEEN   :toDate AND :fromDate   "
			+ "GROUP BY  "
			+ "    CONCAT( "
			+ "        DATE_FORMAT(lrm.collection_date, '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(lrm.collection_date, '%Y') "
			+ "    )",nativeQuery = true)
	public List<Map<String, Object>> getSumOfInterestAndPrinciple(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

	@Query(value = "SELECT  "
			+ "    CONCAT( "
			+ "        DATE_FORMAT(lrm.collection_date, '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(lrm.collection_date, '%Y') "
			+ "    ) AS Month_Year, "
			+ "    CAST(SUM(lrm.intrest) AS UNSIGNED) AS Interest,  "
			+ "    CAST(SUM(lrm.principle) AS UNSIGNED) AS Principle  "
			+ "FROM  "
			+ "    loan_repayment_master lrm "
			+ "WHERE  "
			+ "    lrm.preclose = 0  "
			+ "    AND lrm.collection_date BETWEEN :toDate AND :fromDate AND lrm.branch_id=:branchId "
			+ "GROUP BY  "
			+ "    CONCAT( "
			+ "        DATE_FORMAT(lrm.collection_date, '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(lrm.collection_date, '%Y') )",nativeQuery = true)
	public List<Map<String, Object>> getSumOfInterestAndPrincipleByBranch(@Param("branchId") Integer branchId,@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

	@Query(value = "select CONCAT( "
			+ "        DATE_FORMAT(lm.disbursement_date , '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(lm.disbursement_date , '%Y') "
			+ "    ) AS Month_Year , sum(sm.pfamount)  AS pfAmount from loan_master lm "
			+ " Inner join scheme_master sm ON sm.scheme_id=lm.scheme "
			+ " where lm.status=2  and lm.disbursement_date  BETWEEN :toDate AND :fromDate group By Month_Year; "
			+ " ",nativeQuery = true)
	public List<Map<String, Object>> getSumOfPF(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

	@Query(value = "select CONCAT( "
			+ "        DATE_FORMAT(lm.disbursement_date , '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(lm.disbursement_date , '%Y') "
			+ "    ) AS Month_Year , sum(sm.pfamount)  AS pfAmount from loan_master lm "
			+ " Inner join scheme_master sm ON sm.scheme_id=lm.scheme "
			+ " where lm.status=2 and lm.branchname = :branchId  and lm.disbursement_date  BETWEEN :toDate AND :fromDate group By Month_Year; "
			+ " ",nativeQuery = true)
	public List<Map<String, Object>> getSumOfPFByBranch(@Param("branchId") Integer branchId,@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

	@Query(value = " select  CONCAT( "
			+ "        DATE_FORMAT(sp.salary_date , '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(sp.salary_date , '%Y') "
			+ "    ) AS Month_Year,sum(sp.amount) AS sumOfSalary from salary_paid sp "
			+ "where sp.salary_date  BETWEEN  :toDate AND :fromDate group By Month_Year ",nativeQuery = true)
	public List<Map<String, Object>> getSumOfSalary(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
 
	
	@Query(value = " SELECT CONCAT( "
			+ "        DATE_FORMAT(dm.deposit_date , '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(dm.deposit_date , '%Y') "
			+ "    ) AS Month_Year,sum(dm.amount) as branchExpence FROM lms.deposit_master dm "
			+ " where dm.bank_id=5 and  dm.deposit_date BETWEEN  :toDate AND :fromDate group By Month_Year ",nativeQuery = true)
	public List<Map<String, Object>> getSumOfbranchExpences(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
 
	
	@Query(value = " SELECT CONCAT( "
			+ "        DATE_FORMAT(dm.deposit_date , '%b'),  "
			+ "        ' ',  "
			+ "        DATE_FORMAT(dm.deposit_date , '%Y') "
			+ "    ) AS Month_Year,sum(dm.amount) as branchExpence FROM lms.deposit_master dm "
			+ " where dm.bank_id=5 and  dm.deposit_date BETWEEN  :toDate AND :fromDate  and dm.branch_id=:branchId group By Month_Year ",nativeQuery = true)
	public List<Map<String, Object>> getSumOfbranchExpencesByBranch(@Param("branchId") Integer branchId,@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
 
	
	
}
