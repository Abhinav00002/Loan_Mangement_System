package com.lms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.model.StaffDocument; 

public interface StaffDocumentRepository extends JpaRepository<StaffDocument, Integer>{
	
	
	@Query(value = "SELECT * FROM lms.staff_document_master where staff_id=:id and doc_type_id=1",nativeQuery = true)
	public Optional<StaffDocument> getBystaffId1(int id);

	@Query(value = "SELECT * FROM lms.staff_document_master where staff_id=:id and doc_type_id=2",nativeQuery = true)
	public Optional<StaffDocument> getBystaffId2(int id);

	@Query(value = "SELECT * FROM lms.staff_document_master where staff_id=:id and doc_type_id=3",nativeQuery = true)
	public Optional<StaffDocument> getBystaffId3(int id);

	
	@Query(value = "SELECT * FROM lms.staff_document_master where staff_id=:id and doc_type_id=4",nativeQuery = true)
	public Optional<StaffDocument> getBystaffId4(int id);

	@Query(value = "SELECT * FROM lms.staff_document_master where staff_id=:id and doc_type_id=5",nativeQuery = true)
	public Optional<StaffDocument> getBystaffId5(int id);

	@Query(value = "SELECT * FROM lms.staff_document_master where staff_id=:id and doc_type_id=6",nativeQuery = true)
	public Optional<StaffDocument> getBystaffId6(int id);

	@Query(value = "SELECT * FROM lms.staff_document_master where staff_id=:id and doc_type_id=7",nativeQuery = true)
	public Optional<StaffDocument> getBystaffId7(int id);



}
