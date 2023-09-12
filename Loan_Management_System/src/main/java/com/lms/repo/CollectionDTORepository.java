package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.lms.model.CollectionDTO;

public interface CollectionDTORepository extends JpaRepository<CollectionDTO, Integer> {
	
	  @Procedure
	  @Modifying
	    @Query(nativeQuery = true, value = "CALL InsertDTO(:loanId, :emi)")
	    Integer upsUpdateCollection(@Param("loanId") int loanId, @Param("emi") int emi);
	    

}
