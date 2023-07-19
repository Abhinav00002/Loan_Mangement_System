package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

public	Account findBybankId(Integer accountId);

}
