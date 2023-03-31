package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Staff; 
import com.lms.repo.StaffRepository; 

 

@RestController
@CrossOrigin("*")
@RequestMapping("/staff")
public class StaffController {

	
	
	 
	@Autowired
	private StaffRepository staffRepository;
	
	
	
	//save all the Staff
	@PostMapping("/save")
	public Staff createStaff(@RequestBody Staff staff) throws Exception {
		return staffRepository.save(staff);
		
	}
	
	//get all staff
	@GetMapping("/list")
	public  List<Staff> getStaffs(){
		return staffRepository.findAll();
	}
	
	@GetMapping("/list/{staffId}")
	public Staff getStaff(@PathVariable("staffId") Integer staffId) {
		return this.staffRepository.getStaffById(staffId);
		}
}
