package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.address.Days;
import com.lms.model.address.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer>{

	
//	@Query(value="select  *  from time  tm ",nativeQuery=true)	
	public List<Time> findAll();

	 

	public List<Time> getTimeBytid(Integer timeId);

 
}
