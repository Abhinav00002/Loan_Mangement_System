package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Lender;

public interface LenderRepository extends JpaRepository<Lender, Integer>{

}
