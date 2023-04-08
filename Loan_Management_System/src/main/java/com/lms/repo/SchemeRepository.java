package com.lms.repo;

 

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.model.Scheme;

public interface SchemeRepository extends JpaRepository<Scheme, Long> {

       public Scheme findById(long schemeno);

	public void save(double totalInterestMonthly);

}
