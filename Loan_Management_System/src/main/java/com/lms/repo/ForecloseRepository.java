package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.foreclose.ForeClose;

@Repository
public interface ForecloseRepository extends JpaRepository<ForeClose, Integer> {
	
	@Query(value = "SELECT "
			+ "    lr.loan_id, "
			+ "    SUM(lr.openning_amount) AS totaldue, "
			+ "    SUM(lr.principle) AS principledue, "
			+ "    SUM(lr.intrest) AS totalinterest, "
			+ "    (DATEDIFF(CURRENT_DATE, MAX(lr.collection_date)) * (sm.irr / 365) * SUM(lr.openning_amount)) AS break_period_interest "
			+ "FROM "
			+ "    lms.loan_repayment_master lr "
			+ "JOIN "
			+ "    lms.loan_master lm ON lr.loan_id = lm.loan_id "
			+ "JOIN "
			+ "    lms.scheme_master sm ON lm.scheme = sm.scheme_id "
			+ "WHERE "
			+ "    lr.loan_id = :loanId "
			+ "    AND lr.collection_date IS NULL "
			+ "    AND (lr.coll_amount IS NULL OR lr.coll_amount = 0) "
			+ "    AND lr.preclose = 0  "
			+ "GROUP BY "
			+ "    lr.loan_id ", nativeQuery = true)
	public  Map<String, Object>  getForeCloseDues(@Param("loanId") Integer loanId);
	
	
	@Query(value = "SELECT fm.foreclose_id, fm.loan_id, fm.branch_id, bm.branch_name, fm.center_id, fm.borrower_id, cm.customer_name, cm.mobile_number "
			+ ", fm.coll_amount, fm.coll_by, sm.staff_name, fm.coll_date, fm.status, fm.remark FROM lms.foreclose_master fm  "
			+ "inner join branch_master bm on bm.branch_id=fm.branch_id "
			+ "inner join customer_master cm on fm.borrower_id=cm.customer_id "
			+ "inner join staff_master sm on fm.coll_by = sm.staff_id "
			+ "where fm.coll_date between :toDate and :fromDate", nativeQuery = true)

	 
	public  List<Map<String, Object>> findByddateBetween(LocalDate toDate, LocalDate fromDate);

}
