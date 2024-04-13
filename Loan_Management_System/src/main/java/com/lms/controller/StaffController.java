package com.lms.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.lms.model.Role;
import com.lms.model.Staff;
import com.lms.model.StaffTransfer;
import com.lms.model.User;
import com.lms.model.UserRole;
import com.lms.model.error.ErrorResponse;
import com.lms.repo.RoleRepository;
import com.lms.repo.StaffRepository;
import com.lms.repo.StaffTransferRepository;
import com.lms.repo.UserRepository;
import com.lms.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StaffTransferRepository staffTransferRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// save all the Staff
	@PostMapping("/save")
	public ResponseEntity<?> createStaff(@RequestBody Staff staff) throws Exception {
		
		  if (staff.getContactNo() == null || staff.getContactNo().length() != 10) {
			  String errorMessage = "Invalid contact number. Contact number must be at least 10 characters long.";
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
		     }else if(staff.getDob()==null || staff.getDob().isEmpty()) {
		    	 String errorMessage = "Date of birth is required.";
		    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
			 }else if(staff.getSname()==null || staff.getSname().isEmpty()) {
		    	 String errorMessage = "Name is required.";
		    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
			 }

		User user = new User();
		user.setUsername(staff.getContactNo());
		user.setEmail(staff.getEmail());
		String fullName = staff.getSname();
		String dob = staff.getDob();
		// Extract the year from the date of birth
		int yearOfBirth = Integer.parseInt(dob.substring(0, 4));

		// Extract the first name from the full name
		String[] nameParts = fullName.split(" ");
		String firstName = nameParts[0];
		String password = (firstName + yearOfBirth);
		// Apply additional logic or transformations to the password
		password = password.toLowerCase(); // Convert password to lowercase
		System.out.println(password);

		// Encoding Password using Password Encoder
		user.setPassword(this.bCryptPasswordEncoder.encode(password));

		User local = this.userRepository.findByUsername(user.getUsername());
		System.out.println("LOCAL: "+local);
		if (local != null) {
			System.out.println("User is already there !!");
			String errorMessage = "User with this contact number is already present.";
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
	    } else {
			// user create

	    	 // Save the user
	        User savedUser = this.userRepository.save(user);

	        // Assign role to the user
	        Role role = roleRepository.findByRoleName("Normal"); // Assuming "ROLE_STAFF" is the role you want to assign
	        UserRole userRole = new UserRole();
	        userRole.setUser(savedUser);
	        userRole.setRole(role);
	        
	        // Save UserRole directly
	        savedUser.getUserRoles().add(userRole);
	        userRepository.save(savedUser);
		}

		staff.setStatus(1);
		StaffTransfer staffTransfer = new StaffTransfer();
		// staffTransfer.setEntryBy(user.getId());

		return ResponseEntity.ok(staffRepository.save(staff));

	}

	// get all staff
	@GetMapping("/list")
	public List<Map<String, Object>> getStaffs() {
		return staffRepository.liveStaffList();
	}

	@GetMapping("/staffDetails/list/{staffId}")
	public 	Map<String, Object> getStaff(@PathVariable("staffId") Integer staffId) { 
		
		Map<String, Object> staffDetails=this.staffRepository.getStaffById(staffId); 
		return staffDetails;
	}
	
	
	//STAFF LEFT
	@PostMapping("/left/")
	 public Staff leftStaff(@RequestParam("staffId") Integer staffId, @RequestParam("leftDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate leftDate) {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        System.out.println("STAFF: "+leftDate);
        System.out.println("STAFF: "+staff);
        if (staff != null) {
            staff.setStatus(0);
            staff.setsLeftDate(leftDate);
            staffRepository.save(staff);
        }
        
        return staff;
    }
	
	
	//STAFF TRANSFER
	@PostMapping("/transfer/")
	 public StaffTransfer transferStaff( @RequestBody StaffTransfer staffTransfer) {
		StaffTransfer staff=staffTransferRepository.save(staffTransfer);
       return staff;
   }
	
	//LIST OF  LEFT STAFF
	@GetMapping("/leftStaff/list")
	public List<Map<String, Object>> leftStaffList() {
		List<Map<String, Object>> staff=staffRepository.leftStaffList();
		return staff;
	}
}



























//
//
//public void updateUserRole(String username, String newRoleName) {
//    User user = userRepository.findByUsername(username);
//    if (user != null) {
//        Role newRole = roleRepository.findByRoleName(newRoleName);
//        if (newRole != null) {
//            // Remove all existing roles
//            user.getUserRoles().clear();
//            // Add the new role
//            UserRole userRole = new UserRole();
//            userRole.setUser(user);
//            userRole.setRole(newRole);
//            user.getUserRoles().add(userRole);
//            // Save the updated user
//            userRepository.save(user);
//        } else {
//            // Handle case where new role does not exist
//            throw new IllegalArgumentException("Role does not exist: " + newRoleName);
//        }
//    } else {
//        // Handle case where user does not exist
//        throw new IllegalArgumentException("User does not exist: " + username);
//    }
//}
