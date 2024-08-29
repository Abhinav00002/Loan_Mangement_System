package com.lms.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.CollectionProcedure;

@Repository
public interface CollectionProcedureRepository extends JpaRepository<CollectionProcedure, Integer> {
	
	  @Procedure
	  @Modifying
	    @Query(nativeQuery = true, value = "CALL upsUpdateCollection(:loanId, :collDate, :collAmount, :collType, :collBy, :collBranchId)")
	    Integer upsUpdateCollection(@Param("loanId") int loanId, @Param("collDate") LocalDate collDate,
	                                                      @Param("collAmount") double collAmount, @Param("collType") int collType,
	                                                      @Param("collBy") int collBy, @Param("collBranchId") int collBranchId);
	 
	  
//	  @Query(value =" INSERT INTO collection_master ("
//	  		+ "         loan_id,"
//	  		+ "         coll_date,"
//	  		+ "         coll_ammount,"
//	  		+ "         coll_type,"
//	  		+ "         coll_by,"
//	  		+ "         entry_date,"
//	  		+ "         coll_status,"
//	  		+ "         coll_branch_id,"
//	  		+ "         coll_upadate_repayment,"
//	  		+ "        pending_amount"
//	  		+ "     ) VALUES ("
//	  		+ "         :p_loan_id,"
//	  		+ "         :p_coll_date,"
//	  		+ "         :p_coll_amount,"
//	  		+ "         :p_coll_type,"
//	  		+ "         :p_coll_by, "
//	  		+ "         NOW(),"
//	  		+ "         1,"
//	  		+ "         :p_coll_branch_id,"
//	  		+ "         2,"
//	  		+"          :pending_amount)",nativeQuery = true)
//	  public void updateCollection(@Param("p_loan_id") Integer p_loan_id, @Param("p_coll_date") LocalDate p_coll_date, 
//			  @Param("p_coll_amount") Integer p_coll_amount, @Param("pending_amount") Integer pending_amount, @Param("p_coll_type") String p_coll_type,
//			  @Param("p_coll_branch_id")  Integer p_coll_branch_id,@Param("p_coll_by")  Integer p_coll_by);
//	  
	  

}
