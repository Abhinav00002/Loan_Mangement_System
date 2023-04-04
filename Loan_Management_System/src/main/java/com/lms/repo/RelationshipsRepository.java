package com.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.address.Relationships;

public interface RelationshipsRepository extends JpaRepository<Relationships, Integer> {

	public List<Relationships> findAll();

}
