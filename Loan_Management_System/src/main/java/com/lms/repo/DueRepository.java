package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.address.Due;

public interface DueRepository  extends JpaRepository<Due, Integer>{
	public List<Due> findAll();

}
