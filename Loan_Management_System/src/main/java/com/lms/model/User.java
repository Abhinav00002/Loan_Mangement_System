package com.lms.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

 

@Entity
@Table(name = "user")
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String email;
	private String password;
	
	//user have many roles
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore  // for no circulate dependency
	private Set<UserRole> userRoles=new HashSet<>();
	

	
	
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}







	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	
	
	
	
	
	
	
	public User(long id, String username, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}







	@Override
	public String toString() {
		return "Model [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}




//UserDetails Methods
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> set =new HashSet<>();
		
		
		this.userRoles.forEach(userRole->{
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return set;
	}


	


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;//false
	}  

	
	
	
	
}
