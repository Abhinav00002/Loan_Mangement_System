package com.lms.dto.repository;

 import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lms.dto.LoanDTO;
import com.lms.model.LoanCreation;
@Repository
public interface LoanDTORepository extends JpaRepository<LoanCreation, Long> {

    @Query(value ="SELECT lm.loan_id, lm.bussiness_expences, lm.branchname, lm.borower_co_borower_relation, lm.centername,  "
    		+ "lm.disbursement_date, lm.home_expences, lm.lead_id, lm.lone_expences, lm.name, lm.other_income, lm.purpose,  "
    		+ "lm.scheme, lm.selfincome, lm.spouse_income, lm.spouse_name, lm.sourcedby, lm.meeting_date, lm.meeting_day, "
    		+ " lm.status,   "
    		+ "    COALESCE(sm.staff_name , '-') AS sourcedByName,   "
    		+ "     COALESCE(d.days_name , '-') AS meetingDays,  "
    		+ "    COALESCE(bm.branch_name, '-') AS BranchNames, "
    		+ "    p.purpose_name, "
    		+ "    rm.relation_name, "
    		+ "    scm.scheme_name "
    		+ " "
    		+ "FROM loan_master lm  "
    		+ "inner join branch_master bm ON bm.branch_id=lm.branchname "
    		+ "inner join staff_master sm ON sm.staff_id=lm.sourcedby "
    		+ "inner join purpose p ON p.purpose_id=lm.purpose "
    		+ "inner join relation_master rm ON rm.relation_id=lm.borower_co_borower_relation "
    		+ "inner join scheme_master scm ON scm.scheme_id = lm.scheme "
    		+ "left join days d ON d.days_id=lm.meeting_day "
    		+ "where lm.status=1  ",nativeQuery = true)
    public List<Map<String, Object>> getLoanDTOs();
}  