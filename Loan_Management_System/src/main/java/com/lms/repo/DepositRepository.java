package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer>{
	
	
	
	
	@Query(value = "select "
			+ " * "
			+ " from deposit_master "
			+ " where deposit_date>=:fromDate AND deposit_date<=:toDate and clear_status!=1 ",
			nativeQuery = true)
	  List<Map<String, Object>> getDepositBydepositDate(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
	
		
	

}
