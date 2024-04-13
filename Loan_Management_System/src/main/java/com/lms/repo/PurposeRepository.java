package com.lms.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.address.Purpose;

@Repository
public interface PurposeRepository extends JpaRepository<Purpose, Integer> {

	public  List<Purpose> findAll();
    }
