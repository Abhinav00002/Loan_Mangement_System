package com.lms.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.model.PrintCDS;

public interface PrintCDSRepository extends JpaRepository<PrintCDS, Integer>{
	 //CDS Print Query
	 @Query(value = "  select pd.*, bm.branch_name as branch_name, sm.tenor   from portfolio_due pd 	 	"
	 		+ " INNER JOIN branch_master bm ON bm.branch_id=pd.branchid   "
 	 		+ " INNER JOIN lead_master lem ON lem.leadid =pd.loan_id "
	 		+ " INNER JOIN loan_master lm ON lm.loan_id=lem.leadid  "
	 		+ "INNER JOIN scheme_master sm ON sm.scheme_id =lm.scheme " 
	 		+ " where pd.duedate=:dueDate and pd.branchid=:branchId order by pd.centerid, pd.loan_id"
			 ,nativeQuery = true)
	 public List<PrintCDS >  getCDSData(@Param("dueDate") LocalDate dueDate, @Param("branchId") Integer branchId);


}
