package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Relationships;
import com.lms.repo.RelationshipsRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/relationship")
public class RelationshipsController {

	@Autowired
	private RelationshipsRepository relationshipsRepository;

	@GetMapping("/list")
	public List<Relationships> getRelationships() {
		return relationshipsRepository.findAll();
	}
}
