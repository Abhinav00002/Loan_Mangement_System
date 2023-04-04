package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.address.EmiType;

public interface EmiRepository extends JpaRepository<EmiType, Integer> {
	public List<EmiType> findAll();
 
}
