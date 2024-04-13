package com.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.model.User;
 @Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
//	@Query(value="select  *  from User  us ",nativeQuery=true)	
	 

	public User findByUsername(String username);
	
	 
	  @Procedure(value = "auto_user_role",procedureName = "auto_user_role")
		public void dailyRunPortfolio();
	  
	  
	  
	  @Query(value ="select sm.branch_id from staff_master sm Where sm.contact_no=:userName ",nativeQuery = true)
	  public Integer getBranchIdForUser(@Param("userName") String userName);

	  @Query(value ="select sm.rank from staff_master sm Where sm.contact_no=:userName ",nativeQuery = true)
	  public Integer getRankForUser(@Param("userName") String userName);

 }
