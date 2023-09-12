package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {

	@Procedure(name = "upsUpdateCollection")
	List<Map<String, Object>> upsUpdateCollection(int loanId, LocalDate collDate, double collAmount, int collType,
			int collBy, int collBranchId);
//	    @Procedure
//	    @Query(nativeQuery = true, value = "CALL upsUpdateCollection(:loanId, :collDate, :collAmount, :collType, :collBy, :collBranchId)")
//	    List<Map<String, Object>> upsUpdateCollection(@Param("loanId") int loanId, @Param("collDate") LocalDate collDate,
//	                                                      @Param("collAmount") double collAmount, @Param("collType") int collType,
//	                                                      @Param("collBy") int collBy, @Param("collBranchId") int collBranchId);
//	 

	@Procedure(name = "Collection.getAllCollections")
	@Transactional
	List<Collection> getAllCollections();

	// get Collection Report
	@Query(value = "select coll.loan_id,cusb.customer_name as borrower,cusb.mobile_number,coll_branch_id, sm.staff_name, coll_by,coll_ammount"
			+ " from collection_master coll inner join loan_master lm on coll.loan_id=lm.loan_id"
			+ " inner join lead_master le on le.leadid=lm.lead_id "
			+ " inner join staff_master sm on sm.staff_id=coll.coll_by"
			+ " inner join customer_master cusb on cusb.customer_id=le.borrowerid  "
			+ " where coll.coll_date=:collDate and coll.coll_branch_id=:branchId"
			+ " order by coll.coll_branch_id,le.centerid,coll.loan_id", nativeQuery = true)
	List<Map<String, Object>> getCollectionReport(@Param("collDate") LocalDate collDate,
			@Param("branchId") Integer branchId);

	@Query(value = "SELECT pending_amount FROM collection_master "
			+ "WHERE id = (SELECT MAX(id) FROM collection_master WHERE loan_id = :loanId)", nativeQuery = true)
	public Integer getPendingAmounttoLoanId(@Param("loanId") Integer loanId);

	@Query(value = " SELECT MIN(installment_no)  FROM loan_repayment_master l2 "
			+ "WHERE l2.loan_id = :loanId AND l2.status IN (1, 2)", nativeQuery = true)
	public Integer getInstallmentNoToLoanId(@Param("loanId") Integer loanId);

	@Query(value = " SELECT emi, principle, intrest   FROM"
			+ "			  loan_repayment_master l1 WHERE installment_no = :dueInstallment AND"
			+ "				  l1.loan_id =:loanId", nativeQuery = true)
	public Integer getEMIToLoanIdAndInstaNo(@Param("loanId") Integer loanId,
			@Param("dueInstallment") Integer dueInstallment);
	
	
	
	
	//Sum of Collection For Case In Hand
	@Query(value = " select sum(coll_ammount) from collection_master "
			+ " where coll_branch_id=:branchId and coll_date=:dueDate ",nativeQuery = true)
	public List<Map<String, Object>> getSumOfCollectionAmmount(@Param("branchId") Integer branchId, @Param("dueDate") LocalDate dueDate);
	
	
	
	
}