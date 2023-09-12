package com.lms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

public	Account findBybankId(Integer accountId);

@Query(value = " select dm.*, am.account_name AS accName, am.bank_city AS aBankCity, am.bank_name AS aBName, "
		+ "am.bank_type AS aBType ,am.status AS    aBStatus  "
		+ "from deposit_master dm "
		+ "INNER JOIN account_master am ON  am.id=dm.account_id"
		+ " where dm.account_id=:id",
		nativeQuery = true)
public List<Map<String, Object>> getAccountStatementById(@Param("id") Integer id);

}
