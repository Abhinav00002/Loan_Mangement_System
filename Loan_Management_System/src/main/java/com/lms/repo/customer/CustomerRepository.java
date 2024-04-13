package com.lms.repo.customer;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

		List<Customer> findBycid(Integer borrowerId);
		
		
		
		 @Query(value = " SELECT cm.customer_id AS clientId, cm.customer_name AS Name, cm.fname_hname AS fatherName,"
		 		+ " cm.mobile_number AS contect, "
		 		+ " cm.date_of_birth AS dob, cm.education AS educaion,cm.address_line2 AS addl1,cm.address_line2 AS addl2, "
		 		+ "cm.landmark AS landmark, "
		 		+ " cm.city, cm.pincode , dm.district_name AS district, sm.state_name AS state FROM customer_master cm "
		 		+ " INNER JOIN district_master dm ON dm.district_id=cm.district "
		 		+ " INNER JOIN state_master sm ON sm.state_id=cm.state"
		 		+ " WHERE cm.customer_id IN "
		 		+ "(SELECT lm.borrowerid FROM lead_master lm WHERE lm.leadid IN "
		 		+ "(SELECT lom.lead_id FROM loan_master lom WHERE lom.loan_id = :loanId))", nativeQuery = true)
		   List<Map<String, Object>> getCustomerByloanId(@Param("loanId") Integer loanId);

		 
		 @Query(value = " SELECT cm.customer_id AS clientId, cm.customer_name AS Name, cm.fname_hname AS fatherName,"
			 		+ " cm.mobile_number AS contect, "
			 		+ " cm.date_of_birth AS dob, cm.education AS educaion,cm.address_line2 AS addl1,cm.address_line2 AS addl2, "
			 		+ "cm.landmark AS landmark, "
			 		+ " cm.city, cm.pincode , dm.district_name AS district, sm.state_name AS state FROM customer_master cm "
			 		+ " left JOIN district_master dm ON dm.district_id=cm.district "
			 		+ " left JOIN state_master sm ON sm.state_id=cm.state"
			 		+ " WHERE cm.customer_id IN "
			 		+ "(SELECT lm.co_borrower_id FROM lead_master lm WHERE lm.leadid IN "
			 		+ "(SELECT lom.lead_id FROM loan_master lom WHERE lom.loan_id = :loanId))", nativeQuery = true)
		  List<Map<String, Object>> getCoBorrowerByloanId(@Param("loanId") Integer loanId);

		@Query(value = "SELECT * FROM customer_master WHERE customer_id=:borrowerId", nativeQuery = true)
		public Customer getCustomerBycid(@Param("borrowerId") Integer borrowerId);
		
		
		@Query(value = "SELECT COUNT(*) AS count_result "
				+ "FROM customer_master  "
				+ "WHERE kyc_name = :Bkyctype  "
				+ "AND kyc_number = :BkycNumber "
				+ "AND caution = 1 ", nativeQuery = true)
		public Integer getCautionClient(@Param("Bkyctype") String Bkyctype, @Param("BkycNumber") String BkycNumber); 
		
		 
		@Modifying
		@Transactional
		@Query("UPDATE Customer c SET c.caution = 1 WHERE c.cid = :customerId")
		void updateCautionStatus(@Param("customerId") Integer customerId );

}
