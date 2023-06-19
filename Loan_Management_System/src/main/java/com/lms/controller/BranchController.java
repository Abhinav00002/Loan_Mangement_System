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

import com.lms.model.Branch;
import com.lms.repo.BranchRepository; 


@RestController
@RequestMapping("/branch")
@CrossOrigin("*")
public class BranchController {
	
	 
	
	 
	@Autowired
	private BranchRepository branchRepository;
	
	//save Branch Data 
	@PostMapping("/")
	public Branch createBranch(@RequestBody Branch branch) throws Exception{
		
		branchRepository.save(branch);
		
		return  branch;
		
		
	}
	
	//get Branch by Id
	@GetMapping("/list/{branchId}")
	public Branch getBranch(@PathVariable("branchId") Long branchId) {
		return this.branchRepository.getBranchById(branchId);
		
	}
	
	
	
	
	//get Branches
	@GetMapping("/list")
	public  List<Branch> getBranchs(){
		return branchRepository.findAll();
	}
	
//	
//	@PostMapping("/save")
//	public Branch createBranch(@RequestBody Branch saveBranch) throws Exception{
//		System.out.println(saveBranch);
//		//branch save
//		Branch branch=new Branch();
//			branch.setName(saveBranch.getName());
//			branch.setAddressl1(saveBranch.getAddressl1());
//			branch.setAddressl2(saveBranch.getAddressl2());
//			branch.setLandmark(saveBranch.getLandmark() );
//			branch.setCity(saveBranch.getCity());
//			branch.setDistrict(saveBranch.getDistrict());
//			branch.setState(saveBranch.getState());
//			branch.setPincode(saveBranch.getPincode());
//			branch.setBodate(saveBranch.getBodate());
//			branch.setAid(saveBranch.getAid());
//			branch.setRid(saveBranch.getRid());
//			branch.setCid(saveBranch.getCid());
//			branch.setZid(saveBranch.getZid());
//			branch.setStatus(saveBranch.getStatus());
//			branch.setDatetime(saveBranch.getDatetime());
//		
//			branchRepository.save(branch);
//			return saveBranch;
//	}
}















