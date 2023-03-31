package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.Staff;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
//	@Query(value="select  *  from staff_master  sm ",nativeQuery=true)	
	public List<Staff> findAll();

	
	public Staff getStaffById(Integer staffId);
}
