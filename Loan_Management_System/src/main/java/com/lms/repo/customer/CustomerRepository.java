package com.lms.repo.customer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

		List<Customer> findBycid(Integer borrowerId);
		
		
		
		 @Query(value = "SELECT * FROM customer_master cm WHERE cm.customer_id IN (SELECT lm.borrowerid FROM lead_master lm WHERE lm.leadid IN (SELECT lom.lead_id FROM loan_master lom WHERE lom.loan_id = :loanId))", nativeQuery = true)
		   List<Customer> getCustomerByloanId(@Param("loanId") Integer loanId);

		 
		 @Query(value = "SELECT * FROM customer_master cm WHERE cm.customer_id IN (SELECT lm.co_borrower_id FROM lead_master lm WHERE lm.leadid IN (SELECT lom.lead_id FROM loan_master lom WHERE lom.loan_id = :loanId))", nativeQuery = true)
		   List<Customer> getCoBorrowerByloanId(@Param("loanId") Integer loanId);

		@Query(value = "SELECT * FROM customer_master WHERE customer_id=:borrowerId", nativeQuery = true)
		public Customer getCustomerBycid(@Param("borrowerId") Integer borrowerId);
}
