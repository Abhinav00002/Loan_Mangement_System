package com.lms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.Withdrawal;
@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {

	@Query(value = "SELECT "
			+ " wm.*  "
			+ " FROM "
			+ " lms.withdrawal_master wm where  "
			+ " wm.account_id = :id "
			+ " AND wm.clear_status != 1", nativeQuery = true)
	public List<Map<String, Object>> getSumOfWithdrawal(@Param("id") Integer id);
}
