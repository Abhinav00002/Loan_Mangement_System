package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.address.Kyc; 

@Repository
public interface KycRepository extends JpaRepository<Kyc, Integer>{

//	@Query(value="select  *  from kyc_master  km ",nativeQuery=true)	
	public List<Kyc> findAll();

	
}
