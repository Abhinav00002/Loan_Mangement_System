package com.lms.repo;

import java.util.List;
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

}
