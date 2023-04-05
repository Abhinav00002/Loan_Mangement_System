package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.address.Eduaction;

public interface EducationRepository extends JpaRepository<Eduaction, Integer>{

	public List<Eduaction> findAll();
}
