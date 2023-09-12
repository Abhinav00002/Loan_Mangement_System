package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Region;
import com.lms.repo.RegionRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/region")
public class RegionController {

	@Autowired
	private RegionRepository regionRepository;

	// Get Region By Region Id
	@GetMapping("/list/{regionId}")
	public Region getRegion(@PathVariable("regionId") Integer regionId) {
		return this.regionRepository.getRegionById(regionId);
	}

	// Get Region
	@GetMapping("/list")
	public List<Region> getRegions() {
		return regionRepository.findAll();
	}
}
