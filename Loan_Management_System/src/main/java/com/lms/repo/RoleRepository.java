package com.lms.repo;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Role;

 @Repository
public interface RoleRepository extends JpaRepository <Role,Long> {

	
	
	 Role findByRoleName(String roleName);
}