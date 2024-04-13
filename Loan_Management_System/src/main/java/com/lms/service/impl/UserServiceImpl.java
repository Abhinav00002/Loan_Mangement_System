package com.lms.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.model.Staff;
import com.lms.model.User;
import com.lms.model.UserRole;
import com.lms.repo.RoleRepository;
import com.lms.repo.StaffRepository;
import com.lms.repo.UserRepository;
import com.lms.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private StaffRepository staffRepository;

	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	//creating user
//	@Override
//	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
//			 
//		User local = this.userRepository.findByUsername(user.getUsername());
//		if(local!=null) {
//			System.out.println("User is already there !!");
//			throw new Exception("User already present !!");
//		}else {
//			//user create
//			for(UserRole ur:userRoles) {
//				roleRepository.save(ur.getRole());
//			}
//			user.getUserRoles().addAll(userRoles);
//			System.out.println(user);
//			 
//		}
//		return local;
//	}


	//getting user by username
	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	//delete user by id
	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		this .userRepository.deleteById(userId);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 @Override
	    public Integer getUserBranchId(String username) {
	        return userRepository.getBranchIdForUser(username);
	    }

	    @Override
	    public int getUserRank(String username) {

	        return userRepository.getRankForUser(username);
	    }

	    @Override
	    public boolean isUserAuthorized(String username, int requiredRank, int branchId) {
	        // Implement logic to check if the user is authorized based on their rank and branch
	        int userRank = getUserRank(username);
	        System.out.println("UserrRank: "+userRank);
	        if (userRank == 3) {
	            return false; // User with rank 3 is not permitted to login
	        }
	        if (requiredRank == 1) {
	            return true; // User with rank 1 has access to all data
	        }
	        // For rank 2, check if the user's branch matches the required branch
	        return userRank == 2 && getUserBranchId(username) == branchId;
	    }
}
