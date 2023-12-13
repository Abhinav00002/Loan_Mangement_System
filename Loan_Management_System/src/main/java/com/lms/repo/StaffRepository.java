package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.Staff;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
//	@Query(value="select  *  from staff_master  sm ",nativeQuery=true)	
	public List<Staff> findAll();

	@Query(value="SELECT sm.*,ds.district_name, stm.state_name,bm.branch_name,cm.cluster_name,rm.region_name,zm.zone_name "
			+ " FROM staff_master sm "
			+ "INNER JOIN district_master ds ON ds.district_id=sm.district "
			+ "INNER JOIN state_master stm ON stm.state_id=sm.state "
			+ "INNER JOIN branch_master bm ON bm.branch_id=sm.branch_id "
			+ "INNER Join cluster_master cm ON cm.cluster_id=sm.cluster_id "
			+ "INNER JOIN region_master rm ON rm.region_id=sm.region_id "
			+ "INNER JOIN zone_master zm ON zm.zone_id=sm.zone_id "
			+ "WHERE sm.staff_id=:staffId",
			nativeQuery = true)
	public Map<String, Object> getStaffById(@Param("staffId") Integer staffId);
	
	
	@Query(value = "select sm.staff_id as id, sm.staff_name as sname, sm.staff_father_name, sm.staff_date_of_birth, sm.address_line_1, sm.address_line_2 , sm.email, sm.staff_joinning_date , sm.salary  "
			+ ",sm.contact_no,sm.staff_status ,bm.branch_name from staff_master sm  "
			+ "INNER JOIN branch_master bm ON bm.branch_id=sm.branch_id "
			+ "where sm.staff_status=1", nativeQuery = true)
	public List<Map<String , Object>> liveStaffList();
	 
	@Query(value = "select sm.staff_id, sm.staff_name, sm.staff_father_name, sm.staff_date_of_birth, sm.address_line_1, sm.address_line_2 , sm.email, sm.staff_joinning_date, sm.staff_left_date , sm.salary  "
			+ ",sm.contact_no ,sm.staff_status  ,bm.branch_name from staff_master sm  "
			+ "INNER JOIN branch_master bm ON bm.branch_id=sm.branch_id "
			+ "where sm.staff_status=0 ", nativeQuery = true)
	public List<Map<String , Object>> leftStaffList();
	
 }
