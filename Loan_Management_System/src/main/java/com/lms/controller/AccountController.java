package com.lms.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Account;
import com.lms.model.AccountStatement;
import com.lms.repo.AccountRepository;
import com.lms.repo.AccountStatementRepository;
import com.lms.repo.WithdrawalRepository;

@RestController
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private WithdrawalRepository withdrawalRepository;
	@Autowired
	private AccountStatementRepository accountStatementRepository;

	// get All Accounts in List
	@GetMapping("/account/list/")
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}
	
	   
	@GetMapping("/statement/{accountId}")
	public List<AccountStatement> getAccountStatement(@PathVariable Integer accountId) {
	    System.out.println("ACCOUNT ID: " + accountId);

	    List<Map<String, Object>> accountData = accountStatementRepository.getAccountStatement1(accountId);
	    double balance = 0;
	    List<AccountStatement> acc1 = new ArrayList<AccountStatement>();

	    for (Map<String, Object> row : accountData) {
	        AccountStatement acc = new AccountStatement();

	        // Set default values for depositAmount, id, withdrawalAmount, salaryAmount, and salaryId if they are null.
	        acc.setDepositAmount(row.get("depositAmount") != null ? (Double) row.get("depositAmount") : 0d);
	        acc.setId(row.get("id") != null ? (Integer) row.get("id") : 0);
	        acc.setWithdrawalAmount(row.get("withdrawalAmount") != null ? (Double) row.get("withdrawalAmount") : 0d);

	       
	        Integer salaryAmount = (Integer) row.get("salaryAmount");
	        acc.setSalaryAmount(salaryAmount != null ? salaryAmount : 0L);

//	        acc.setSalaryAmount(row.get("salaryAmount") != null ? (long) row.get("salaryAmount") : 0l);
	        acc.setSalaryId(row.get("salaryId") != null ? (Integer) row.get("salaryId") : 0);
	        acc.setSalaryDate((Date) row.get("salaryDate"));
	        acc.setSalaryEntryDate((Date) row.get("salaryEntryDate"));

	        // Set common values
	        acc.setDcrDate((Date) row.get("dcrDate"));
	        acc.setDepositDate((Date) row.get("depositDate"));
	        acc.setBranchName((String) row.get("branchName"));
	        acc.setAccountId((Integer) row.get("accountId"));
	        acc.setAccountName((String) row.get("accountName"));
	        acc.setAccountBankId((Integer) row.get("accountBankId"));
	        acc.setAccountBankName((String) row.get("accountBankName"));
	        acc.setAccountBankCity((String) row.get("accountBankCity"));
	        acc.setAccountBankType((String) row.get("accountBankType"));
	        acc.setAccountBankStatus((Integer) row.get("accountBankStatus"));

	        balance = balance + acc.getDepositAmount() - acc.getWithdrawalAmount() - acc.getSalaryAmount();
	        acc.setBalance(balance);
	        acc1.add(acc);
	    }

	    return acc1;
	}

	//Save Account 
	@PostMapping("/account/save/")
	public Account saveAccount(@RequestBody Account account) {
		accountRepository.save(account);
		return account;
	}
	
	
	
	// Between toDate And fromDate
	@GetMapping("/statement/sortToDate/{accountId}/{toDate}/{fromDate}")
	public List<AccountStatement> getAccountStatementBetweentoDateAndfromDate(@PathVariable Integer accountId,
			@PathVariable("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@PathVariable("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
	    System.out.println("ACCOUNT ID: " + accountId);
	    System.out.println("toDate: " + toDate);
	    System.out.println("fromDate: " + fromDate);

	    List<Map<String, Object>> accountData = accountStatementRepository.getAccountStatementBetweentoDateAndfromDate(accountId,toDate,fromDate);
	    double balance = 0;
	    List<AccountStatement> acc1 = new ArrayList<AccountStatement>();

	    for (Map<String, Object> row : accountData) {
	        AccountStatement acc = new AccountStatement();

	        // Set default values for depositAmount, id, withdrawalAmount, salaryAmount, and salaryId if they are null.
	        acc.setDepositAmount(row.get("depositAmount") != null ? (Double) row.get("depositAmount") : 0d);
	        acc.setId(row.get("id") != null ? (Integer) row.get("id") : 0);
	        acc.setWithdrawalAmount(row.get("withdrawalAmount") != null ? (Double) row.get("withdrawalAmount") : 0d);

	       
	        Integer salaryAmount = (Integer) row.get("salaryAmount");
	        acc.setSalaryAmount(salaryAmount != null ? salaryAmount  : 0L);

//	        acc.setSalaryAmount(row.get("salaryAmount") != null ? (long) row.get("salaryAmount") : 0l);
	        acc.setSalaryId(row.get("salaryId") != null ? (Integer) row.get("salaryId") : 0);
	        acc.setSalaryDate((Date) row.get("salaryDate"));
	        acc.setSalaryEntryDate((Date) row.get("salaryEntryDate"));

	        // Set common values
	        acc.setDcrDate((Date) row.get("dcrDate"));
	        acc.setDepositDate((Date) row.get("depositDate"));
	        acc.setBranchName((String) row.get("branchName"));
	        acc.setAccountId((Integer) row.get("accountId"));
	        acc.setAccountName((String) row.get("accountName"));
	        acc.setAccountBankId((Integer) row.get("accountBankId"));
	        acc.setAccountBankName((String) row.get("accountBankName"));
	        acc.setAccountBankCity((String) row.get("accountBankCity"));
	        acc.setAccountBankType((String) row.get("accountBankType"));
	        acc.setAccountBankStatus((Integer) row.get("accountBankStatus"));

	        balance = balance + acc.getDepositAmount() - acc.getWithdrawalAmount() - acc.getSalaryAmount();
	        acc.setBalance(balance);
	        acc1.add(acc);
	    }

	    return acc1;
	}

	//method service
}
