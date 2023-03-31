package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>{

}
