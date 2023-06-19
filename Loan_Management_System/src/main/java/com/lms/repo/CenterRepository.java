package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Center;
import com.lms.model.address.District;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

	
	//get all center
	public List<Center> findAll();
	//get Center by Id
	public List<Center> getCenterByncid(Long centerID) ;
	  public List<Center> getCenterByBname(Long branch_name) ;
	
}
