package com.lms.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.model.Documents;

public interface DocumentsRepository extends JpaRepository<Documents, Integer>{

	@Query(value = "SELECT * FROM lms.document_master where loan_id=:id and doc_type_id=1",nativeQuery = true)
	public Optional<Documents> getByloanId1(int id);

	@Query(value = "SELECT * FROM lms.document_master where loan_id=:id and doc_type_id=2",nativeQuery = true)
	public Optional<Documents> getByloanId2(int id);

	@Query(value = "SELECT * FROM lms.document_master where loan_id=:id and doc_type_id=3",nativeQuery = true)
	public Optional<Documents> getByloanId3(int id);

	
	@Query(value = "SELECT * FROM lms.document_master where loan_id=:id and doc_type_id=4",nativeQuery = true)
	public Optional<Documents> getByloanId4(int id);

	@Query(value = "SELECT * FROM lms.document_master where loan_id=:id and doc_type_id=5",nativeQuery = true)
	public Optional<Documents> getByloanId5(int id);

	@Query(value = "SELECT * FROM lms.document_master where loan_id=:id and doc_type_id=6",nativeQuery = true)
	public Optional<Documents> getByloanId6(int id);

	@Query(value = "SELECT * FROM lms.document_master where loan_id=:id and doc_type_id=7",nativeQuery = true)
	public Optional<Documents> getByloanId7(int id);
	
	
	//query for select loanid for loanagreement (not uploaded)
    @Query(value="SELECT  lm.loan_id,lm.lead_id,lm.disbursement_date,lm.status,cm.customer_name,lem.borrowerid,cm.mobile_number "
    		+ "FROM loan_master lm "
    		+ ""
    		+ "left JOIN lead_master lem ON lem.leadid=lm.lead_id "
    		+ "left JOIN customer_master cm ON cm.customer_id=lem.borrowerid "
    		+ "WHERE NOT EXISTS ("
    		+ "    SELECT 1"
    		+ "    FROM document_master dm"
    		+ "    WHERE dm.loan_id = lm.loan_id"
    		+ "    AND dm.doc_type_id = 5 "
    		+ ")",nativeQuery = true)
        public List<Map<String , Object>> getAllLoanIdForLoanAgreement();
}
