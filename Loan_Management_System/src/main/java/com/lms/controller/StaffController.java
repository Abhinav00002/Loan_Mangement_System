package com.lms.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Role;
import com.lms.model.Staff;
import com.lms.model.StaffTransfer;
import com.lms.model.User;
import com.lms.model.UserRole;
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
	public Staff createStaff(@RequestBody Staff staff) throws Exception {

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
		if (local != null) {
			System.out.println("User is already there !!");
			throw new Exception("User already present !!");
		} else {
			// user create

			this.userRepository.save(user);

		}

		staff.setStatus(1);
		StaffTransfer staffTransfer = new StaffTransfer();
		// staffTransfer.setEntryBy(user.getId());

		return staffRepository.save(staff);

	}

	// get all staff
	@GetMapping("/list")
	public List<Staff> getStaffs() {
		return staffRepository.findAll();
	}

	@GetMapping("/list/{staffId}")
	public Staff getStaff(@PathVariable("staffId") Integer staffId) {
		return this.staffRepository.getStaffById(staffId);
	}
}
