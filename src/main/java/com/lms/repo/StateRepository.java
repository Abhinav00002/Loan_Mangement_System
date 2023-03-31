package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.address.States;


@Repository
public interface StateRepository extends JpaRepository<States, Integer> {
 
//	@Query(value="select  *  from state_master  sm ",nativeQuery=true)	
	public List<States> findAll();
	 
	
	//get by Id
	
	public States getStatesById(Integer stateId);
}
