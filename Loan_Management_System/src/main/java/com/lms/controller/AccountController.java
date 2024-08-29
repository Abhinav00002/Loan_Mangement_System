package com.lms.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		List<AccountStatement> acc1 = new ArrayList<>();

		for (Map<String, Object> row : accountData) {
			AccountStatement acc = new AccountStatement();

			// Set default values for depositAmount, id, withdrawalAmount, salaryAmount, and
			// salaryId if they are null.
			acc.setDepositAmount(row.get("depositAmount") != null ? (Double) row.get("depositAmount") : 0d);
			acc.setId(row.get("id") != null ? (Integer) row.get("id") : 0);
			acc.setWithdrawalAmount(row.get("withdrawalAmount") != null ? (Double) row.get("withdrawalAmount") : 0d);

			BigInteger bigIntegerValue = (BigInteger) row.get("salaryAmount");
			Integer salaryAmount = (bigIntegerValue != null) ? bigIntegerValue.intValue() : 0;
			acc.setSalaryAmount(salaryAmount != null ? salaryAmount : 0L);

			acc.setSalaryId(row.get("salaryId") != null ? (Integer) row.get("salaryId") : 0);
			acc.setSalaryDate((Date) row.get("salaryDate"));
			acc.setSalaryEntryDate((Date) row.get("salaryEntryDate"));

			acc.setDcrDate((Date) row.get("dcrDate"));
			acc.setDepositDate((Date) row.get("depositDate"));
			acc.setRemark((String) row.get("remark"));
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

	// Save Account
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

		List<Map<String, Object>> accountData = accountStatementRepository
				.getAccountStatementBetweentoDateAndfromDate(accountId, toDate, fromDate);

		double balance = 0;
		List<AccountStatement> acc1 = new ArrayList<>();

		for (Map<String, Object> row : accountData) {
			AccountStatement acc = new AccountStatement();

			// Set default values for depositAmount, id, withdrawalAmount, salaryAmount, and
			// salaryId if they are null.
			acc.setDepositAmount(row.get("depositAmount") != null ? (Double) row.get("depositAmount") : 0d);
			acc.setId(row.get("id") != null ? (Integer) row.get("id") : 0);
			acc.setWithdrawalAmount(row.get("withdrawalAmount") != null ? (Double) row.get("withdrawalAmount") : 0d);

			BigInteger bigIntegerValue = (BigInteger) row.get("salaryAmount");
			Integer salaryAmount = (bigIntegerValue != null) ? bigIntegerValue.intValue() : 0;
			acc.setSalaryAmount(salaryAmount != null ? salaryAmount : 0L);

			acc.setRemark((String) row.get("remark"));

			acc.setSalaryId(row.get("salaryId") != null ? (Integer) row.get("salaryId") : 0);
			acc.setSalaryDate((Date) row.get("salaryDate"));
			acc.setSalaryEntryDate((Date) row.get("salaryEntryDate"));

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

	// get Opening Amount ForAccount Statement
	@GetMapping("/openingAmount/{accountId}/{fromDate}")
	public List<Map<String, Object>> getOpeningAmountfromDate(@PathVariable Integer accountId,
			@PathVariable("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
		System.out.println("accountID: " + accountId + " fromDate " + fromDate);
		return accountStatementRepository.getOpeningAmountfromDate(accountId, fromDate);
	}

	// get all total Due amount and total interest
	@GetMapping("/totalamount/{toDate}/{fromDate}")
	public List<Map<String, Object>> getTotalAmount(
			@PathVariable("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@PathVariable("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
		return accountRepository.getTotalDue(toDate, fromDate);

	}

	@GetMapping("/alldump/excel/{toDate}/{fromDate}")
	public ResponseEntity<byte[]> getAllPortfoliosExcel(
			@PathVariable("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@PathVariable("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate)
			throws IOException {
		List<Map<String, Object>> dumps = accountRepository.getTotalDamp(toDate, fromDate);

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Total Due and Collection Data");

		// Create header row
		Row headerRow = sheet.createRow(0);
		String[] columns = { "Loan Id", "Branch", "Center Id", "Borrower Name", "Mobile No", "Co-Borrower Name",
				"Address", "Disbursment Date", "Finance Amount", "EMI" };
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// Populate data rows
		int rowNum = 1;
		for (Map<String, Object> data : dumps) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(data.get("loan_id").toString());
			row.createCell(1).setCellValue(data.get("branchName").toString());
			row.createCell(2).setCellValue(data.get("centername").toString());
			row.createCell(3)
					.setCellValue(data.get("borrowerName").toString() + "-" + data.get("borrowerid").toString());
			row.createCell(4).setCellValue(data.get("borrowerContectNumber").toString());
			row.createCell(5)
					.setCellValue(data.get("coBorrowerName").toString() + "-" + data.get("co_borrower_id").toString());
			row.createCell(6)
					.setCellValue(data.get("borrowerAddl1").toString() + " " + data.get("borrowerAddl2").toString()
							+ " " + data.get("borrowerDistrict").toString() + "" + " "
							+ data.get("borowerState").toString() + "");
			row.createCell(7).setCellValue(data.get("disbursement_date").toString());
			row.createCell(8).setCellValue(data.get("financeAmount").toString());
			row.createCell(9).setCellValue(data.get("emi").toString());
		}

		// Convert the Excel workbook to a byte array
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		byte[] excelBytes = outputStream.toByteArray();

		// Set response headers and return the Excel file
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

		headers.setContentDisposition(ContentDisposition.builder("attachment").filename("portfolio_data.xlsx").build());

		return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
	}

}
