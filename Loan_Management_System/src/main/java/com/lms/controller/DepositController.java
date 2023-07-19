package com.lms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Account;
import com.lms.model.Bank;
import com.lms.model.Branch;
import com.lms.model.Deposit;
import com.lms.model.User;
import com.lms.repo.AccountRepository;
import com.lms.repo.BankRepository;
import com.lms.repo.BranchRepository;
import com.lms.repo.DepositRepository;

@RestController
@CrossOrigin("*")
public class DepositController {

	@Autowired
	private DepositRepository depositRepository;
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private BranchRepository branchRepository;
	
	@PostMapping("/deposit/save/")
	public Deposit createDeposit(@RequestBody Deposit deposit) throws Exception {
		
		 // Retrieve the authentication object from the security context
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    // Get the user details from the authentication object
	    User userDetails = (User) authentication.getPrincipal();
	    // Get the current user's ID as a Long
	    Integer userId = (int) userDetails.getId();
	    // Get the current user's name
	    String username = userDetails.getUsername();

	    deposit.setEntryBy(userId);
	Integer accountId=	deposit.getBankId();
	long branchId=(long)deposit.getBranchId();
	Branch branch=branchRepository.getBranchById(branchId);
	Account account=accountRepository.findById(accountId).orElse(null);
	if(account!=null && branch!=null) {
		deposit.setBankId(account.getBankId());
		deposit.setBankName(account.getBankName());
		deposit.setBankType(account.getBankType());
		deposit.setBranchId((int)branchId);
		deposit.setBranchName(branch.getName());
	}else {
        // Handle the case when the bank with the provided bankId is not found.
        throw new Exception("Bank not found for bankId: " + accountId);
    }
		depositRepository.save(deposit);
		return deposit;
		
	}
}
