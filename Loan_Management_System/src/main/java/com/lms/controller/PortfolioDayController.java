package com.lms.controller;

import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.PortfolioDay;
import com.lms.repo.PortfolioDayRepository;

@RestController
@CrossOrigin("*")
public class PortfolioDayController {
	
	
	
	@Autowired
	private PortfolioDayRepository portfolioDayRepository;
	
	
	//GET ALL PORTFOLIO OF DAY
	 @GetMapping("/all")
	    public List<Map<String, Object>> getAllPortfolios() {
	        List<Map<String, Object>> portfolios = portfolioDayRepository.findAllData();
	        return  portfolios;
	    }
	 
	 
	 
	 @GetMapping("/all/excel")
	 public ResponseEntity<byte[]> getAllPortfoliosExcel() throws IOException {
	     List<Map<String, Object>> portfolios = portfolioDayRepository.findAllData();

	     Workbook workbook = new XSSFWorkbook();
	     Sheet sheet = workbook.createSheet("Portfolio Data");

	     // Create header row
	     Row headerRow = sheet.createRow(0);
	     String[] columns = {"Loan Id", "Branch Id", "Center Id", "Borrower Name", "Mobile No", "Co-Borrower Name", "Disbursment Date", "Due Date", "EMI Pending", "Opening Amount", "Pending Installment", "Entry Date","Finance Amount","EMI"};
	     for (int i = 0; i < columns.length; i++) {
	         Cell cell = headerRow.createCell(i);
	         cell.setCellValue(columns[i]);
	     }

	     // Populate data rows
	     int rowNum = 1;
	     for (Map<String, Object> data : portfolios) {
	         Row row = sheet.createRow(rowNum++);
	         row.createCell(0).setCellValue(data.get("loan_id").toString());
	         row.createCell(1).setCellValue(data.get("branchid").toString());
	         row.createCell(2).setCellValue(data.get("centerid").toString());
	         row.createCell(3).setCellValue(data.get("borrowerName").toString() + "-" + data.get("borrowerid").toString());
	         row.createCell(4).setCellValue(data.get("contectNo").toString());
	         row.createCell(5).setCellValue(data.get("coBorrowerName").toString() + "-" + data.get("co_borrower_id").toString());
	         row.createCell(6).setCellValue(data.get("disbursement_date").toString());
	         row.createCell(7).setCellValue(data.get("due_date").toString());
	         row.createCell(8).setCellValue(data.get("emipending").toString());
	         row.createCell(9).setCellValue(data.get("openning_amount").toString());
	         row.createCell(10).setCellValue(data.get("pendinginst").toString());
	         row.createCell(11).setCellValue(data.get("entrydate").toString());
	         row.createCell(11).setCellValue(data.get("financeAmount").toString());
	         row.createCell(11).setCellValue(data.get("sEmi").toString());
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
