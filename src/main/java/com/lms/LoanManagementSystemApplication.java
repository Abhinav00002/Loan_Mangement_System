package com.lms;

   
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
 
 
@SpringBootApplication
public class LoanManagementSystemApplication  implements CommandLineRunner{

	
//	  @Autowired 
//	 private UserService userService;
	 
	  
//	  @Autowired
//	  private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(LoanManagementSystemApplication.class, args);
	}

	
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
		System.out.println("Starting code");
		
//		
//		User user=new User();
//		user.setUsername("Abhinav");
//		user.setEmail("abh@gmail.com");
//		user.setPassword(this.bCryptPasswordEncoder.encode("abhinav"));
//		
//		Role role1=new Role();
//		role1.setRoleId(42L);
//		role1.setRoleName("Admin");
//		
//		
//		
//		
//		Set<UserRole> userRoleSet=new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		
//		
//		userRoleSet.add(userRole);
//		
//		
//		
//	User user1=	this.userService.createUser(user, userRoleSet);
//	System.out.println(user1.getUsername());
	}catch (Exception e) { 
		e.printStackTrace();
	}
	}
}
