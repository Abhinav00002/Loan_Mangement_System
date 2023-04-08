package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.address.Purpose;

public interface PurposeRepository extends JpaRepository<Purpose, Integer>{

	public  List<Purpose> findAll();
}
