package com.lms.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Account;
import com.lms.repo.AccountRepository;

@RestController
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;

	// get All Accounts in List
	@GetMapping("/account/list/")
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}
	
	
	@GetMapping("/account_statement/{id}")
	public List<Map<String, Object>> getAccountStatement(@PathVariable("id") Integer id){
		return accountRepository.getAccountStatementById(id);
	}
}
