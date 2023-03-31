package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

 
import com.lms.model.address.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

	
//	@Query(value="select  *  from region_master  rm ",nativeQuery=true)	
	public List<Region> findAll();
	
	
	public Region getRegionById(Integer regionId);

}
