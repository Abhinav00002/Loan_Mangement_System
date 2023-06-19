package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.address.Days;


@Repository
public interface DaysRepository extends JpaRepository<Days, Integer>{


//	@Query(value="select  *  from days  dy ",nativeQuery=true)	
	public List<Days> findAll();

	public List<Days> getDayBydid(Integer dayId);
}
