package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Zone;
import com.lms.repo.ZoneRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/zone")
public class ZoneController {

	@Autowired
	private ZoneRepository zoneRepository;

	// Get Zone By Zone
	@GetMapping("/list")
	public List<Zone> getZones() {
		return zoneRepository.findAll();
	}

	// Get Zone By Zone Id
	@GetMapping("/list/{zoneId}")
	public Zone getZone(@PathVariable("zoneId") Integer zoneId) {
		return this.zoneRepository.getZoneById(zoneId);
	}

}
