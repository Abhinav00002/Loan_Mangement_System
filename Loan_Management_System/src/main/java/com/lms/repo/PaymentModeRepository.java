package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.address.PaymentMode;

public interface PaymentModeRepository extends JpaRepository<PaymentMode, Integer>{
	public List<PaymentMode>  findAll();
}
