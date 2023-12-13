package com.lms.repo;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

public	Account findBybankId(Integer accountId);
 


}
