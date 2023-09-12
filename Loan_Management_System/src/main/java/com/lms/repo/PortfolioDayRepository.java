package com.lms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.model.PortfolioDay;

@Repository
public interface PortfolioDayRepository extends JpaRepository<PortfolioDay, Integer> {
	
	
	@Query(value = " select pd.* ,cb.customer_name as borrowerName,cb.mobile_number as contectNo, sm.emi as sEmi, sm.loneamount as financeAmount,"
			+ " cbs.customer_name as coBorrowerName from portfolio_day pd"
			+ " INNER JOIN customer_master cb ON cb.customer_id=pd.borrowerid "
			+ "INNER JOIN customer_master cbs ON cbs.customer_id=pd.co_borrower_id "
			+ " INNER JOIN loan_master lm ON lm.loan_id=pd.loan_id "
			+ "INNER JOIN scheme_master sm ON sm.scheme_id=lm.scheme"
			, nativeQuery = true)
 	public List<Map<String, Object>> findAllData();
}
