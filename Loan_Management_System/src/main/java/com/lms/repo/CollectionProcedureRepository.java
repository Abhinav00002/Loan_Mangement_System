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
	 

}
