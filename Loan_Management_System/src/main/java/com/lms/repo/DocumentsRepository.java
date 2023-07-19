package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Documents;

public interface DocumentsRepository extends JpaRepository<Documents, Integer>{

}
