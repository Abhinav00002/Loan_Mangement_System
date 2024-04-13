package com.lms.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lms.model.PrintCDS;

public interface PrintCDSRepository extends JpaRepository<PrintCDS, Integer>{
	 //CDS Print Query
	 @Query(value = "  select pd.*, bm.branch_name as branch_name, sm.tenor,stm.staff_name   "
	 		+ "from portfolio_due pd   "
	 		+ "INNER JOIN branch_master bm ON bm.branch_id=pd.branchid  "
	 		+ "       INNER JOIN loan_master lm ON lm.loan_id=pd.loan_id  "
	 		+ " INNER JOIN lead_master lem ON lem.leadid =lm.lead_id   "
	 		+ "  INNER JOIN scheme_master sm ON sm.scheme_id =lm.scheme  "
	 		+ "   Inner join staff_master stm ON stm.staff_id=lem.manage_by    "
	 		+ "  where pd.duedate=:dueDate and pd.branchid=:branchId"
	 		+ " order by pd.centerid,lm.loan_id ",nativeQuery = true)
	 public List<PrintCDS >  getCDSData(@Param("dueDate") LocalDate dueDate, @Param("branchId") Integer branchId);


}
