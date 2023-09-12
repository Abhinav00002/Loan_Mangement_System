package com.lms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Cluster;
import com.lms.repo.ClusterRepository;

@RestController
@RequestMapping("/cluster")
@CrossOrigin("*")
public class ClusterController {

	@Autowired
	private ClusterRepository clusterRepository;

	// Get Cluster By ClusterId
	@GetMapping("/list/{clusterId}")
	public Cluster getCluster(@PathVariable("clusterId") Integer clusterId) {
		return this.clusterRepository.getClusterById(clusterId);

	}

	// Get All Cluster By Cluster
	@GetMapping("/list")
	public List<Cluster> getCluster() {
		List<Cluster> clusterList = new ArrayList<Cluster>();
		clusterList = clusterRepository.findAll();
		return clusterList;
	}

}
