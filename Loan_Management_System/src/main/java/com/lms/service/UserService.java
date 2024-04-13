

package com.lms.service;

import java.util.Set;
 

import com.lms.model.User;
import com.lms.model.UserRole;



public interface UserService {
	
	//creating user
//	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	
	//get user by username
	public User getUser(String username);
	
	//delete user by id
	public void deleteUser(Long userId);
	
	
	
	
	
	
	
	
	
	
	
	 Integer getUserBranchId(String username);
	    int getUserRank(String username);
	    boolean isUserAuthorized(String username, int requiredRank, int branchId);

}
