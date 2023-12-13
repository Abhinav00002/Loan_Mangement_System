package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.model.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
	
	@Query(value = " SELECT im.id, im.lender_id, im.amount, im.investment_type, im.investment_date, im.interest_rate, im.interest_start_date, im.status, im.close_date, im.entry_by, im.close_by, lm.lender_name "
			+ "      FROM investment_master im "
			+ "      INNER JOIN lender_master lm ON lm.lender_id=im.lender_id", nativeQuery = true)
	public  List<Investment> getAllInvestments();

}
