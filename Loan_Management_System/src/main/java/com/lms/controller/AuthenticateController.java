package com.lms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.config.JwtUtils;

import com.lms.model.JwtRequest;
import com.lms.model.JwtResponse;
import com.lms.model.User;
import com.lms.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	// generate token API
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//		System.out.println(jwtRequest.getUsername()+jwtRequest.getPassword());
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not Found ");
		}
		///// Authenticate
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
//		System.out.println( userDetails);

		String token = this.jwtUtils.generateToken(userDetails);
//		System.out.println("JWT "+ token);
		return ResponseEntity.ok(new JwtResponse(token));
	}

//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
//	}

	private void authenticate(String username, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED  " + e.getMessage());

		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials  " + e.getMessage());
			// TODO: handle exception
		}
	}

	// login details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {

		return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
	}
}
