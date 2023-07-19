package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

}
