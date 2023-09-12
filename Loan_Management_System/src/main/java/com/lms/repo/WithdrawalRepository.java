package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Integer> {

}
