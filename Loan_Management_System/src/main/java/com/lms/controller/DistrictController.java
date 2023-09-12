package com.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.District;
import com.lms.repo.DistrictRepository;

@RestController
@RequestMapping("/district")
@CrossOrigin("*")
public class DistrictController {

	@Autowired
	private DistrictRepository districtRepository;

	//
	@GetMapping("/list")
	public List<District> getDistrict() {
		List<District> list = new ArrayList<>();
		list = districtRepository.findAll();
		return list;

	}

	// Get district by districtId
	@GetMapping("/list/{districtId}")
	public District getDistrict(@PathVariable("districtId") Integer districtId) {
		return this.districtRepository.getDistrictById(districtId);
	}

	// get district by stateid
	@GetMapping("/state/{state_id}")
	public List<District> getDistrictByStateId(@PathVariable("state_id") Integer state_id) {
		return districtRepository.getDistrictByStateId(state_id);
	}

}