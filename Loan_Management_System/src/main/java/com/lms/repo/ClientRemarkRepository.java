package com.lms.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.ClientRemark;

@Repository
public interface ClientRemarkRepository extends JpaRepository<ClientRemark, Integer> {

	
	@Query(value = "SELECT  cr.client_remark_id, cr.loan_id, cr.borrower_id,cm.customer_name, cr.branch_id,bm.branch_name, "
			+ " cr.center_id, cr.contact_no,  "
			+ "cr.client_manage_by, sm.staff_name AS manageby,cr.remark_type,rt.remark AS remarkName, cr.remark, cr.remark_date, cr.remark_by , sm1.staff_name AS remarkBy  "
			+ "FROM lms.client_remark cr "
			+ "inner join customer_master cm ON cm.customer_id=cr.borrower_id "
			+ "inner JOIN branch_master bm ON bm.branch_id=cr.branch_id "
			+ "INNER JOIN staff_master sm ON  cr.client_manage_by=sm.staff_id "
			+ "INNER JOIN staff_master sm1 ON cr.remark_by=sm1.staff_id "
			+ "INNER JOIN remark_type rt ON cr.remark_type=rt.remark_id ",nativeQuery = true)
	public  List<Map<String, Object>> findremark();
	
	 @Modifying 
	 @Transactional
	 @Query(value = "update ClientRemark cr set cr.remarkBy=:remarkBy "
	 		+ " , cr.remarkDate=:remarkDate "
	 		+ " , cr.remarkType=:remarkType"
	 		+ " where cr.clientRemarkId=:clientRemarkId")
	 public void updateremarkById( @Param("remarkDate")  Date remarkDate, @Param("remarkBy") Integer remarkBy,
			 @Param("remarkType") Integer remarkType, @Param("clientRemarkId") Integer clientRemarkId);
	

}
