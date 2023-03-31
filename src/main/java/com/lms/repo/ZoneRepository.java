package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.address.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

	
//	@Query(value="select  *  from zone_master  zm ",nativeQuery=true)	
	public List<Zone> findAll();
	
	
	//zone By Id
		public Zone getZoneById(Integer zoneId);

}
