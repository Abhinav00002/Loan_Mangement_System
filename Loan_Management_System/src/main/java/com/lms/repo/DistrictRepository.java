package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.address.District;


@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
	
	//get district by state Id
//	public District findAll(int state_id);
//      @Query(value="select  *  from district_master  dm ",nativeQuery=true)	
	
      public List<District> findAll();
	
      public District getDistrictById(Integer districtId);

 	
 	
//      @Query(value="SELECT * FROM lms.district_master where state_id=?",nativeQuery=true)	
  		//get district by Id
	  public List<District> getDistrictByStateId(Integer state_id);
	

}
