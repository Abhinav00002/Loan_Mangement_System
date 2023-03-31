package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

	
	//get all center
	public List<Center> findAll();
}
