package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Collection;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

}
