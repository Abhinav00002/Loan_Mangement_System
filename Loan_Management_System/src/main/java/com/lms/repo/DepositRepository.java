package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Integer>{

}
