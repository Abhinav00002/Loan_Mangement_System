package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.lms.model.User;
 @Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
//	@Query(value="select  *  from User  us ",nativeQuery=true)	
	 

	public User findByUsername(String username);
	
	
	
}
