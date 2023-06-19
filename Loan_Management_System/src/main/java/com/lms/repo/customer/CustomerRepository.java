package com.lms.repo.customer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

		List<Customer> findBycid(Integer borrowerId);
		
}
