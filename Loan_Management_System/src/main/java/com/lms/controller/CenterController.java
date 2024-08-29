package com.lms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Center;
import com.lms.model.address.District;
import com.lms.repo.BranchRepository;
import com.lms.repo.CenterRepository;
import com.lms.repo.LeadRepository;
import com.lms.repo.LoanRepaymentRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/newCenter")
public class CenterController {

	@Autowired
	private CenterRepository centerRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
	@Autowired
	private LeadRepository leadRepository;

	// save center
	@PostMapping("/")
	public Center createCenter(@RequestBody Center center) throws Exception {

		Integer centerId = centerRepository.getmaxcenterId(center.getBname());
		System.out.println("CenterId" + centerId);
		if (centerId == null) {
			centerId = 1;
		} else {
			centerId = centerId + 1;
		}
		center.setCenterId(centerId);
		centerRepository.save(center);
		return center;

	}

	// get All center
	@GetMapping("/list/")
	public List<Center> getCenter() {
		List<Center> center = new ArrayList<Center>();
		center = centerRepository.findAll();
		return center;
	}

	// get district by branchId
	@GetMapping("/branch/{branch_name}")
	public List<Map<String, Object>> getCenterByBname(@PathVariable("branch_name") Long branch_name) {
		return centerRepository.getCenterByBname(branch_name);
	}

	// Modify Center

	@PostMapping("/update-center/")
	public ResponseEntity<Map<String, String>> updateCenter(@RequestParam("loanId") int loanId,
			@RequestParam("newCenterId") int newCenterId) {
		try {
			// Update centerId in loanrepayment table
			loanRepaymentRepository.updatecenterIdByloanId((long) loanId, newCenterId);
			// Update centerId in lead table
			leadRepository.updatecenterIDByLeadID(loanId, (long) newCenterId);
			Map<String, String> response = new HashMap<>();
			response.put("message", "Center updated successfully for loanId: " + loanId);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// Get Center For Client Details
	@GetMapping("/centerByLoan/{loanId}")
	public List<Map<String, Object>> getCenterByLoanId(@PathVariable("loanId") Integer loanId) {
		List<Map<String, Object>> center = centerRepository.getCenterDataByLoanId(loanId);
		System.out.println(center);
		return center;
	}

	// Get Live center to branch Id
	@GetMapping("/liveCenter/{branchId}")
	public List<Map<String, Object>> getLiveCenter(@PathVariable("branchId") Integer branchId) {
		return centerRepository.getLiveCenterBybranchId(branchId);
	}

	@GetMapping("/center/{centerid}")
	public List<Map<String, Object>> getCenterByCenterId(@PathVariable("centerid") Integer centerid) {
		return centerRepository.getCenterBycenterId(centerid);
	}

	// GET CLIENT DETAILS IN LIVE CENTER
//	@GetMapping("/clientDetails/")
//	public List<Map<String, Object>> getClientOfCenterByCenterId(@RequestParam("branchId") Integer branchId,@RequestParam("centerType") Integer centerType,@RequestParam("centerId") Integer centerId){
//		
//		System.out.println("ceterId :"+centerId+" centerType : "+centerType+" branchId"+branchId);
//		return centerRepository.getCenterClientData(branchId,centerType,centerId);
//	}
//	

//update center Manage by (Center Type : Group & Individual)
	@PostMapping("/update-center-manageby/")
	public ResponseEntity<Map<String, String>> updateCenterManageBy(@RequestParam("ceManageBy") Integer ceManageBy,
			@RequestParam("centerId") Integer centerId, @RequestParam("bname") Long bname,
			@RequestParam(name = "leadId", required = false) Integer leadId) {
		Map<String, String> response = new HashMap<>();
		try {
			if (bname == null) {
				throw new IllegalArgumentException("bname cannot be null");
			}

			String manageBy = Integer.toString(ceManageBy);

			centerRepository.updatecenterManageBy(ceManageBy, centerId, bname);

			if (leadId == null) {
				leadRepository.updateManageBy(manageBy, (long) centerId, bname);
			} else {
				leadRepository.updateManageBy1(manageBy, (long) centerId, bname, leadId);
			}

			response.put("message", "Center ManageBy updated successfully");

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// update center Manage by (Center Type : Group & Individual)
	@PostMapping("/update-center-time/")
	public ResponseEntity<Map<String, String>> updateCentertime(@RequestParam("centerId") Integer centerId,
			@RequestParam("bname") Long bname, @RequestParam("time") Integer time) {
		Map<String, String> response = new HashMap<>();
		try {
			if (bname == null) {
				throw new IllegalArgumentException("bname cannot be null");
			}
			if (time != null) {
				centerRepository.updatecenterTime(time, centerId, bname);

				response.put("message", "Center time updated successfully");
			}
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	// Get Client Details in Live center
	@GetMapping("/clientDetails/{branchId}/{centerId}/{centerType}")
	public List<Map<String, Object>> getClientDetailsInLiveCenter(@PathVariable("centerId") Integer centerId,
			@PathVariable("branchId") Integer branchId, @PathVariable("centerType") Integer centerType) {
		List<Map<String, Object>> clientDetails = centerRepository.getCenterClientDetails(centerType, centerId,
				branchId);
		return clientDetails;
	}

	// Get Center by BranchID and centerType
	@GetMapping("/center_by_branch_and_centerType/{branchId}/{centerType}")
	public List<Map<String, Object>> getCenterBybranchIdAndcenterType(@PathVariable("branchId") Integer branchId,
			@PathVariable("centerType") Integer centerType) {
		List<Map<String, Object>> center = centerRepository.centerBybranchIdAndcenterId(branchId, centerType);
		return center;
	}
}
