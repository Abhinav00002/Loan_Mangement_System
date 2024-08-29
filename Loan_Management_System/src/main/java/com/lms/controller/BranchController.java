package com.lms.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Branch;
import com.lms.repo.BranchRepository;
import com.lms.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/branch")
@CrossOrigin("*")
public class BranchController {

	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;

	// save Branch Data
	@PostMapping("/")
	public Branch createBranch(@RequestBody Branch branch) throws Exception {

		branchRepository.save(branch);

		return branch;

	}

	// Get Branch by Id
	@GetMapping("/list/{branchId}")
	public ResponseEntity<Branch> getBranchById(@PathVariable("branchId") Long branchId, Principal principal) {
		String username = principal.getName();
		Integer userBranchId = userServiceImpl.getUserBranchId(username);
		int userRank = userServiceImpl.getUserRank(username);

		if (userRank == 1 && userBranchId == 1) {
			userBranchId = branchId.intValue();
		}

		if (!userServiceImpl.isUserAuthorized(username, userRank, userBranchId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		Branch branch = this.branchRepository.getBranchById(branchId);
		if (branch == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(branch);
	}

	// Get Branches
	@GetMapping("/list")
	public ResponseEntity<List<Branch>> getBranches(Principal principal) {
		String username = principal.getName();
		Integer userBranchId = userServiceImpl.getUserBranchId(username);
		int userRank = userServiceImpl.getUserRank(username);
		System.out.println("User Branch Id: " + userBranchId);
		System.out.println("User Rank: " + userRank);

		// Check if the user is authorized to access the list of branches
		if (!userServiceImpl.isUserAuthorized(username, userRank, userBranchId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		// If user rank is 1, they can access all branches, so return all branches
		if (userRank == 1 && userBranchId == 1) {
			List<Branch> branches = branchRepository.findAll();
			return ResponseEntity.ok(branches);
		}

		// If user rank is 2, they can access only their branch's data
		if (userRank == 2 || (userBranchId != 1 && userRank == 1)) {
			Branch userBranch = branchRepository.getBranchById(userBranchId.longValue());
			if (userBranch != null) {
				List<Branch> branches = new ArrayList<>();
				branches.add(userBranch);
				return ResponseEntity.ok(branches);
			}
		}

		// If user rank is 3, they are not permitted to access branches data
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	//
	// @PostMapping("/save")
	// public Branch createBranch(@RequestBody Branch saveBranch) throws Exception{
	// System.out.println(saveBranch);
	// //branch save
	// Branch branch=new Branch();
	// branch.setName(saveBranch.getName());
	// branch.setAddressl1(saveBranch.getAddressl1());
	// branch.setAddressl2(saveBranch.getAddressl2());
	// branch.setLandmark(saveBranch.getLandmark() );
	// branch.setCity(saveBranch.getCity());
	// branch.setDistrict(saveBranch.getDistrict());
	// branch.setState(saveBranch.getState());
	// branch.setPincode(saveBranch.getPincode());
	// branch.setBodate(saveBranch.getBodate());
	// branch.setAid(saveBranch.getAid());
	// branch.setRid(saveBranch.getRid());
	// branch.setCid(saveBranch.getCid());
	// branch.setZid(saveBranch.getZid());
	// branch.setStatus(saveBranch.getStatus());
	// branch.setDatetime(saveBranch.getDatetime());
	//
	// branchRepository.save(branch);
	// return saveBranch;
	// }
}
