package com.lms.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Role;
import com.lms.model.User;
import com.lms.model.UserRole;
import com.lms.repo.UserRepository;
import com.lms.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//
	// //creating user
	// @PostMapping("/")
	// public User createUser(@RequestBody User user) throws Exception {
	//
	// //Encoding Password using Password Encoder
	// user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
	//
	// Set<UserRole> roles=new HashSet<>();
	//
	// Role role=new Role();
	// role.setRoleId(45L);
	// role.setRoleName("Normal");
	//
	//
	// UserRole userRole=new UserRole();
	// userRole.setUser(user);
	// userRole.setRole(role);
	//
	// roles.add(userRole);
	//
	// return this.userService.createUser(user,roles);
	// }

	// get user by username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);

	}

	// delete the user by id
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}
	
	 @Scheduled(cron = "0 0 * * * *") // Run daily at midnight  
	    public Integer runDailyProcedure() {
	        userRepository.dailyRunPortfolio();  
	        return 1;
	    }
}
