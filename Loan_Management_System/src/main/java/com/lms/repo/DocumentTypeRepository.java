package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.address.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer>{

}
