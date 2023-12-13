package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Account;
import com.lms.model.Branch;
import com.lms.model.User;
import com.lms.model.Withdrawal;
import com.lms.repo.AccountRepository;
import com.lms.repo.BranchRepository;
import com.lms.repo.WithdrawalRepository;

@RestController
@CrossOrigin("*")
public class WithdrawalController {

	@Autowired
	private WithdrawalRepository withdrawalRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	
	@PostMapping("/withdrawal/save/")
	public Withdrawal createWithdrawal(@RequestBody Withdrawal withdrawal) throws Exception {

		// Retrieve the authentication object from the security context
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Get the user details from the authentication object
		User userDetails = (User) authentication.getPrincipal();
		// Get the current user's ID as a Long
		Integer userId = (int) userDetails.getId();
		// Get the current user's name
		String username = userDetails.getUsername();
		
		withdrawal.setEntryBy(userId);
		Integer accountId = withdrawal.getBankId();
		long branchId = (long) withdrawal.getBranchId();
		Branch branch = branchRepository.getBranchById(branchId);
		System.out.println(branch);
		withdrawal.setAccountId(accountId);
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account != null && branch != null) {
			withdrawal.setBankId(account.getBankId());
			withdrawal.setBankName(account.getBankName());
			withdrawal.setBankType(account.getBankType());
			withdrawal.setBranchId((int) branchId);
			withdrawal.setBranchName(branch.getName());
		} else {
			// Handle the case when the bank with the provided bankId is not found.
			throw new Exception("Bank not found for bankId: " + accountId);
		}
		withdrawalRepository.save(withdrawal);
		return withdrawal;

	}

	
	
	
}
