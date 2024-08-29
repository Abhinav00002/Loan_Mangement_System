package com.lms.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.ClientRemark;
import com.lms.repo.ClientRemarkRepository;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.customer.CustomerRepository;

@RestController
@CrossOrigin("*")
public class ClientRemarkController {

	@Autowired
	private ClientRemarkRepository clientRemarkRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private LoanCreationRepository loanCreationRepository;

	@GetMapping("/clientRemark/list")
	public List<Map<String, Object>> getClientRemark() {
		return clientRemarkRepository.findremark();
	}

	@PostMapping("/add/clientRemark/")
	public ResponseEntity<Object> saveClientRemark(@RequestBody ClientRemark clientRemark) {
		System.out.println(clientRemark);
		// change coutious status to 1 if client coutious
		Long loanId = Integer.toUnsignedLong(clientRemark.getLoanId());
		Integer borrowerId = clientRemark.getBorrowerId();
		System.out.println("LOANiD " + loanId + " borrower ID " + borrowerId);
		customerRepository.updateCautionStatus(borrowerId);
		loanCreationRepository.updatecautionToloanId(loanId);
		ClientRemark savedClientRemark = clientRemarkRepository.save(clientRemark);

		Map<String, Object> response = new HashMap<>();
		response.put("clientRemark", savedClientRemark);
		response.put("message", "Client remarked successfully.");

		return ResponseEntity.ok(response);
	}

	@PostMapping("/update/clientRemark/")
	public ResponseEntity<Object> updateClientRemark(
			@RequestParam("remarkDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date remarkDate,
			@RequestParam("loanId") Long loanId, @RequestParam("remarkId") Integer remarkId,
			@RequestParam("borrowerId") Integer borrowerId, @RequestParam("remarkType") Integer remarkType,
			@RequestParam("remarkby") Integer remarkby) {

		// change coutious status to 1 if client coutious
		System.out.println("LOANiD " + loanId + " borrower ID " + borrowerId);
		customerRepository.updateCautionStatus(borrowerId);
		loanCreationRepository.updatecautionToloanId(loanId);
		clientRemarkRepository.updateremarkById(remarkDate, remarkby, remarkType, remarkId);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Client remarked successfully.");

		return ResponseEntity.ok(response);
	}

}
