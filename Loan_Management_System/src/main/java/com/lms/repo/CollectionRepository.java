package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
			+ " where coll.coll_date=:collDate and coll.coll_branch_id=:branchId and coll_status!=3"
			+ " order by coll.coll_branch_id,le.centerid,coll.loan_id", nativeQuery = true)
	List<Map<String, Object>> getCollectionReport(@Param("collDate") LocalDate collDate,
			@Param("branchId") Integer branchId);

	@Query(value = "SELECT pending_amount FROM collection_master "
			+ "WHERE id = (SELECT MAX(id) FROM collection_master WHERE loan_id = :loanId) and coll_status!=3", nativeQuery = true)
	public Integer getPendingAmounttoLoanId(@Param("loanId") Integer loanId);

	@Query(value = " SELECT MIN(installment_no)  FROM loan_repayment_master l2 "
			+ "WHERE l2.loan_id = :loanId AND l2.status IN (1, 2)", nativeQuery = true)
	public Integer getInstallmentNoToLoanId(@Param("loanId") Integer loanId);

	@Query(value = " SELECT emi, principle, intrest   FROM"
			+ "	loan_repayment_master l1 WHERE installment_no = :dueInstallment AND"
			+ "	l1.loan_id =:loanId", nativeQuery = true)
	public Integer getEMIToLoanIdAndInstaNo(@Param("loanId") Integer loanId,
			@Param("dueInstallment") Integer dueInstallment);
	
 
	
	
	
	
	//Sum of Collection For Case In Hand
	@Query(value = " select sum(coll_ammount) from collection_master "
			+ " where coll_branch_id=:branchId and coll_date=:dueDate and coll_status!=3",nativeQuery = true)
	public Integer getSumOfCollectionAmmount(@Param("branchId") Integer branchId, @Param("dueDate") LocalDate dueDate);
	
	//Sum of Deposit For Case In Hand
	@Query(value = " select sum(amount) from deposit_master "
			+ " where branch_id=:branchId and dcr_date=:dueDate and clear_status!=1 ",nativeQuery = true)
	public Integer getSumOfDepositAmmount(@Param("branchId") Integer branchId, @Param("dueDate") LocalDate dueDate);
	
	//Sum of OtherCollection and Disbursementamount For Case In Hand
		@Query(value = " select sum(loneamount) as financeamount,sum(pfamount) as otheramount  "
				+ "from loan_master lom "
				+ "inner join lead_master lem on lem.leadid=lom.lead_id "
				+ "and lem.branchid=:branchId "
				+ "inner join scheme_master s on s.scheme_id=lom.scheme  "
				+ "where lom.disbursement_date=:dueDate   and lom.status=2 ",
				nativeQuery = true)
		public List<Map<String, Object>> getSumOfDisbursementAndOtherCollectionAmmount(@Param("branchId") Integer branchId, @Param("dueDate") LocalDate dueDate);
		
	//Sum of Withdrawal Amount as Received Amount for Case In hand
		@Query(value = "select sum( amount) as receivedAmount from withdrawal_master"
				+ " where branch_id = :branchId and  deposit_date=:dueDate and clear_status!=1"
				,nativeQuery = true)
		public Integer getSumOfReceivedAmount(@Param("branchId") Integer branchId, @Param("dueDate") LocalDate dueDate);
		
		
		//Sum of Opening 
		@Query(value = "   SELECT SUM(amount) AS  collection "
				+ "FROM deposit_master "
				+ "WHERE DATE(dcr_date) = DATE_SUB(:dueDate, INTERVAL 1 DAY) and branch_id=:branchId and clear_status!=1  and account_id=4"
				, nativeQuery = true)
		public Integer getSumOfOpeningAmount(@Param("branchId") Integer branchId, @Param("dueDate") LocalDate dueDate);
		
		
		
		
		
		
		//Payment Remove
		  @Procedure
		  @Modifying
		    @Query(nativeQuery = true, value = "CALL payment_remove(:loanId, :paymentDate, :deletedBy)")
		   public void payment_remove(@Param("loanId") int loanId,@Param("paymentDate") LocalDate paymentDate, @Param("deletedBy") int deletedBy);
		   
		
		  
		  
		  
		  //Total due and Collection to by branch Id and between due date
		  @Query(value = "SELECT "
		  		+ " lm.loan_id,  lm.coll_amount AS totalCollAmount, "
		  		+ " CASE  WHEN lm.coll_amount = 0 THEN lm.emi "
		  		+ " ELSE 0  "
		  		+ " END AS due"
		  		+ " FROM loan_repayment_master lm WHERE lm.branch_id = 2",nativeQuery = true)
		  public List<Map<String, Object>>getTotaldueAndCollection(@Param("branchId") Integer beanchId, @Param("toDate") LocalDate todate,@Param("fromDate") LocalDate fromDate);
	
}