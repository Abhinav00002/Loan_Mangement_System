package com.lms;

   
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; 
 
 
@SpringBootApplication

@EnableScheduling
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
		
		}catch (Exception e) { 
		e.printStackTrace();
		}
	}
}
