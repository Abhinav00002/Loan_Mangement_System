package com.lms.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.lms.model.Account;
import com.lms.model.Branch;
import com.lms.model.Center;
import com.lms.model.Collection;
import com.lms.model.CollectionProcedure;
import com.lms.model.Customer;
import com.lms.model.Deposit;
import com.lms.model.Lead;
import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;
import com.lms.model.PrintCDS;
import com.lms.model.Scheme;
import com.lms.model.User;
import com.lms.model.Withdrawal;
import com.lms.model.address.Days;
import com.lms.model.address.Time;
import com.lms.repo.AccountRepository;
import com.lms.repo.BranchRepository;
import com.lms.repo.CenterRepository;
import com.lms.repo.CollectionProcedureRepository;
import com.lms.repo.CollectionRepository;
import com.lms.repo.DaysRepository;
import com.lms.repo.DepositRepository;
import com.lms.repo.LeadRepository;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.LoanRepaymentRepository;
import com.lms.repo.PrintCDSRepository;
import com.lms.repo.SchemeRepository;
import com.lms.repo.TimeRepository;
import com.lms.repo.UserRepository;
import com.lms.repo.WithdrawalRepository;
import com.lms.repo.customer.CustomerRepository;

@CrossOrigin("*")
@RestController
public class CollectionController {

	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private LoanCreationRepository loanCreationRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DaysRepository daysRepository;
	@Autowired
	private TimeRepository timeRepository;
	@Autowired
	private CenterRepository centerRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	private SchemeRepository schemeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CollectionProcedureRepository collectionProcedureRepository;
	@Autowired
	private WithdrawalRepository withdrawalRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private DepositRepository depositRepository;
	@Autowired
	private PrintCDSRepository printCDSRepository;

	// Get Collection Data
	@GetMapping("/collection-data/")
	public ResponseEntity<List<PrintCDS>> getData(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId) {

		// Fetch the loan repayment data based on due date and branch ID
		List<PrintCDS> cdsData = printCDSRepository.getCDSData(dueDate, branchId);

		return ResponseEntity.ok(cdsData);
	}

	 
	// Print Pdf of Collection Data (CDS Print)
	@RequestMapping(value = { "/CDS/" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getByteTC(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId) throws IOException, DocumentException, ParseException {
		System.out.println(dueDate);
		List<PrintCDS> cdsData = printCDSRepository.getCDSData(dueDate, branchId);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();

		 document.setPageSize(PageSize.A4.rotate());
		PdfWriter writer = PdfWriter.getInstance(document, out);

		// Create a custom footer event handler
		PdfPageEventHelper eventHandler = new PdfPageEventHelper() {
			public void onEndPage(PdfWriter writer, Document document) {

				// Add the footer to the bottom of the page
				PdfContentByte canvas = writer.getDirectContent();
				// ...

				canvas.beginText();
				try {
					canvas.setFontAndSize(BaseFont.createFont(), 10);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String footerLine1 = "MEXWELL MICRO FOUNDATION";
				String footerLine2 = "Registered Office: Dhiraj Singh Nabada, Bulandshar Uttar Pradesh 203203 ";
				String footerLine3 = "Email: mexwellmicrofoundation@gmail.com";

				// Calculate the width of the longest line to align them properly
				float maxTextWidth = Math.max(canvas.getEffectiveStringWidth(footerLine1, false),
						Math.max(canvas.getEffectiveStringWidth(footerLine2, false),
								canvas.getEffectiveStringWidth(footerLine3, false)));

				// Calculate the x-position to center the text
				float x = (document.right() + document.left() - maxTextWidth) / 2;
				float y = document.bottomMargin(); // Set the y-position to the bottom margin of the page

				// Show each line of the footer text separately
				canvas.showTextAligned(Element.ALIGN_LEFT, footerLine1, x + 100, y, 0);
				canvas.showTextAligned(Element.ALIGN_LEFT, footerLine2, x, y - 12, 0); // Adjust the vertical

				canvas.showTextAligned(Element.ALIGN_LEFT, footerLine3, x + 90, y - 24, 0); // Adjust the vertical

				canvas.endText();

				// Create a custom Header Image event handler
				// Add the logo at the top of the page
				try {
					Image logoImage = Image.getInstance("vitarma.jpeg");
					logoImage.scaleToFit(100, 100);
					logoImage.setAlignment(Element.ALIGN_CENTER);
					logoImage.setBackgroundColor(BaseColor.WHITE);

					float logoWidth = logoImage.getScaledWidth();
					float logoHeight = logoImage.getScaledHeight();
					float pageWidth = document.right() - document.left();
					float x1 = (pageWidth - logoWidth) / 2;
					float y1 = document.top() - 30;

					logoImage.setAbsolutePosition(x1, y1);
					document.add(logoImage);

					 float spaceHeight = 25f;
					    float contentY = y1 - spaceHeight;
					    Paragraph space = new Paragraph();
					    space.setSpacingBefore(spaceHeight);
					    document.add(space);

				} catch (DocumentException | IOException e) {
					e.printStackTrace();
				}
			}
		};

		// Set the footer event handler for the writer
		writer.setPageEvent(eventHandler);

		document.open();

		 document.setPageSize(PageSize.A4.rotate());

		Integer checkCenterId = 0;
		Integer totalCollection = 0;
		Integer serialNumber = 1;

		PdfPTable table = new PdfPTable(11);
		float[] columnWidths = { 1f, 1.5f, 3f, 3f, 2f, 5f, 1f, 1f, 1f, 1f, 5f };
		table.setWidths(columnWidths);
		table.setWidthPercentage(100);
		table.setSpacingAfter(10);
		table.setSpacingBefore(50);
		table.setTotalWidth(530);
		// table.setSpacingBefore(10f);
		// table.setSpacingAfter(10f);

		// Set table header style
		Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, new BaseColor(0, 0, 0));
		Font cellFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, new BaseColor(0, 0, 0));

		 
		for (PrintCDS collectionData : cdsData) {
			Integer EMI = collectionData.getEmi();
			Integer centerId = collectionData.getCenterid();
			String branchName = collectionData.getBranchName();
			String day=collectionData.getDaysName();
			float time=collectionData.getTime();
			int centerType=collectionData.getCenterType();
			Date date1=collectionData.getDuedate();
			String printDate = new SimpleDateFormat("dd-MM-yy").format(date1); 

			if (checkCenterId != collectionData.getCenterid()) {

				if (checkCenterId != 0) {
//					total
					// Add the total collection row for the previous center
					PdfPCell totalCell = new PdfPCell(new Phrase("Total Collection:", headerFont));
					totalCell.setColspan(6);
					totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(totalCell);

					// Create a new row for the total collection for the previous center
					PdfPCell totalCollectionCell = new PdfPCell(new Phrase(String.valueOf(totalCollection), cellFont));
					totalCollectionCell.setColspan(5);
					table.addCell(totalCollectionCell);

					// Reset the totalCollection variable for the new center
					totalCollection = 0;
					document.add(table);
					document.newPage();
					table = new PdfPTable(11);
//					  columnWidths = { 2f, 4f, 2.5f, 3.8f, 3.5f, 4f, 4f, 4.5f, 4.5f };
					table.setWidths(columnWidths);
					table.setWidthPercentage(100);
					table.setSpacingAfter(10);
					table.setSpacingBefore(50);
					table.setTotalWidth(530);
				}

				Paragraph para = new Paragraph("\n");
//				para.setAlignment(Element.ALIGN_CENTER);
				document.add(para);

				// Update the current center ID and reset the serial number
				checkCenterId = centerId;
				serialNumber = 1;

				// Header

				PdfPCell headerCell = new PdfPCell();
				headerCell.setNoWrap(true);
				headerCell.setMinimumHeight(20f);

				 
				 
				// Create the cell for the header text
				headerCell = new PdfPCell();
				// headerCell.setNoWrap(true);
				headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPaddingBottom(10f);
				headerCell.setPaddingLeft(10f);
				headerCell.setNoWrap(true);
				headerCell.setUseAscender(true);
				headerCell.setUseDescender(true);
				headerCell.setFixedHeight(30f);
				headerCell.setLeading(12f, 0f);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				String printHeader="";
				if(centerType==1) {
					printHeader= "CDS "+ branchName+" as on " + printDate + " Day "+day;
				}else {
					printHeader= "CDS "+ branchName+" as on " + printDate + " Centre No "+centerId+ " Day "+day+" Time "+time+" AM ";
				}
				headerCell = new PdfPCell(new Phrase(printHeader, headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				headerCell.setColspan(11);
				table.addCell(headerCell);

				 
				 // Set remaining header cells
				headerCell = new PdfPCell();
				// headerCell.setNoWrap(true);
				headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPaddingBottom(10f);
				headerCell.setPaddingLeft(10f);
				headerCell.setNoWrap(true);
				headerCell.setUseAscender(true);
				headerCell.setUseDescender(true);
				headerCell.setFixedHeight(30f);
				headerCell.setLeading(12f, 0f);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				headerCell = new PdfPCell(new Phrase("S. NO.", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

				headerCell = new PdfPCell(new Phrase("Loan Id", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

				headerCell = new PdfPCell(new Phrase("Borrower Name", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

				headerCell = new PdfPCell(new Phrase("Co Borrower Name", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

				headerCell = new PdfPCell(new Phrase("Mobile No", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

				headerCell = new PdfPCell(new Phrase("Address", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

				headerCell = new PdfPCell(new Phrase("Due Amt.", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("Total INST.", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("P. INST.", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

				headerCell = new PdfPCell(new Phrase("P. Amt. ", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

			

				headerCell = new PdfPCell(new Phrase("Client Sign", headerFont)); 
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);

			}
//			cell
			// Add table row
			PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(serialNumber), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getLoanId()), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getCustomerName()), cellFont)); 
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getCoBorrowerName()), cellFont)); 
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getMobileNumber()), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(
					String.valueOf(collectionData.getAddressLine1() + " " + collectionData.getAddressLine2()),
					cellFont)); 
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf((int) Math.floor(EMI)), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			

			cell = new PdfPCell(new Phrase(String.valueOf(collectionData.gettenor()), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getPendinginst()), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getEmipending()), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);


			table.addCell("");

			serialNumber++;

			// Calculate total collection
			totalCollection += EMI; 
			
			// Check if it's the last record
		    if (cdsData.indexOf(collectionData) == cdsData.size() - 1) {
		        // This is the last record, so add the total collection for the last center.
		        PdfPCell totalCell = new PdfPCell(new Phrase("Total Collection:", headerFont));
		        totalCell.setColspan(6);
		        totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        table.addCell(totalCell);

		        PdfPCell totalCollectionCell = new PdfPCell(new Phrase(String.valueOf(totalCollection), cellFont));
		        totalCollectionCell.setColspan(5);
		        table.addCell(totalCollectionCell);
		    }
		    
		    checkCenterId = centerId;
		    
		    // Check if it's the last record, and close the table and document if it is
		    if (cdsData.indexOf(collectionData) == cdsData.size() - 1) {
		        document.add(table);
		        document.close();
		    }
			 

		}

		 

	 
		ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=" + branchId + ".pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	
	
	

	// Collection Update (CDS Update)
	@PostMapping("/cds-update/")
	@Transactional
	public List<Map<String, Object>> postData(@RequestBody Map<String, Object> collectionData) {

		System.out.println("Collection DATA = " + collectionData);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userDetails = (User) authentication.getPrincipal();
		Integer userId = (int) userDetails.getId();

		List<Map<String, Object>> results = new ArrayList<>();

		try {

			List<Map<String, Object>> collectionItems = (List<Map<String, Object>>) collectionData
					.get("collectionData");

			for (Map<String, Object> item : collectionItems) {

				Integer loanId = (Integer) item.get("loanId");
				Integer collectionAmount = (Integer) item.get("emi");
				LocalDate dueDate = LocalDate.parse((String) item.get("dueDate"));
				Integer branchId = (Integer) item.get("branchId");

				System.out.println("LOAN ID : " + loanId + " EMI : " + collectionAmount + " Due Date : " + dueDate
						+ " branchId : " + branchId + "");

				// to Query SELECT pending_amount INTO pending_amount FROM collection_master
				// WHERE id = (SELECT MAX(id) FROM collection_master WHERE loan_id = p_loan_id);
				Integer pendAmount = collectionRepository.getPendingAmounttoLoanId(loanId);
				System.out.println("PENDING AMOUNT : " + pendAmount);
				// to Query SELECT MIN(installment_no) INTO p_installment_no FROM
				// loan_repayment_master l2 WHERE l2.loan_id = p_loan_id AND status IN (1, 3);
				Integer due = collectionRepository.getInstallmentNoToLoanId(loanId);
				System.out.println("INSTALLMENT NO : " + due);
				// to query SELECT emi, principle, intrest INTO p_emi, principle, interest FROM
				// loan_repayment_master l1 WHERE installment_no = p_installment_no AND
				// l1.loan_id = p_loan_id;
				Integer dueEmi = collectionRepository.getEMIToLoanIdAndInstaNo(loanId, due);
				System.out.println("EMI : " + dueEmi);

				// Check for null values and skip if any value is null
//				if (pendAmount == null || due == null || dueEmi == null) {
//					// Handle the error or log it
//					Map<String, Object> errorResult = new HashMap<>();
//					errorResult.put("error", "One or more required values are missing for loanId: " + loanId);
//					results.add(errorResult);
//					continue; // Skip to the next item
//				}

				if (pendAmount == null) {
					pendAmount = 0;
				}
				if (due == null) {
					due = 0;
				}
				if (dueEmi == null) {
					dueEmi = 0;
				}

				Integer pendingAmount = pendAmount;
				Integer netCollection = pendingAmount + collectionAmount;
				do {
					int dueInstallment = due;

					int emi = dueEmi;

					int collectedAmount = 0;
					if (netCollection == emi) {
						collectedAmount = netCollection;
					} else if (netCollection > emi) {
						collectedAmount = emi;
					} else {
						collectedAmount = netCollection;
					}

					Integer result = collectionProcedureRepository.upsUpdateCollection(loanId, dueDate, collectedAmount,
							1, userId, branchId);
					System.out.println("RESULT = " + result);

					Map<String, Object> resultMap = new HashMap<>();

					resultMap.put("result", result);
					results.add(resultMap);

					netCollection = netCollection - collectedAmount;

				} while (netCollection > 0);

			}
		} catch (IndexOutOfBoundsException e) {

			e.printStackTrace();
			return null;
		}
		return results;
	}

	// get Disbursement register
	@GetMapping("/list/")
	public ResponseEntity<List<Map<String, Object>>> findBytoDateTofromDate(
			@RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
		List<Map<String, Object>> loan = loanCreationRepository.findByddateBetween(toDate, fromDate);
		return ResponseEntity.ok(loan);
	}

	// Advance Payment
	@PostMapping("/advance_payment/")
	@Transactional
	public Integer advancePaymentData(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId, @RequestParam("emi") double collectionAmount,
			@RequestParam("loanId") Integer loanId, @RequestParam("collectionBy") Integer userId) {
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			User userDeails = (User) authentication.getPrincipal();
//			Integer userId = (int) userDeails.getId();
		System.out.println("DUE DATE: " + dueDate + "\t BRANCH ID: " + branchId + "\tEMI: " + collectionAmount
				+ "\tLOAN ID: " + loanId + "\tCOLLECTION BY: " + userId);

		Integer pendAmount = collectionRepository.getPendingAmounttoLoanId(loanId);
		Integer due = collectionRepository.getInstallmentNoToLoanId(loanId);
		Integer dueEmi = collectionRepository.getEMIToLoanIdAndInstaNo(loanId, due);

		// Set pendAmount to 0 if it is null
		if (pendAmount == null) {
			pendAmount = 0;
		}

		Integer result;
		try {

			Integer pendingAmount = pendAmount;
			Integer netCollection = (int) (pendingAmount + collectionAmount);
			do {
				int dueInstallment = due;

				int emi = dueEmi;

				int collectedAmount = 0;
				if (netCollection == emi) {
					collectedAmount = netCollection;
				} else if (netCollection > emi) {
					collectedAmount = emi;
				} else {
					collectedAmount = netCollection;
				}

				result = collectionProcedureRepository.upsUpdateCollection(loanId, dueDate, collectedAmount, 1, userId,
						branchId);
				System.out.println("RESULT = " + result);

				netCollection = netCollection - collectedAmount;

			} while (netCollection > 0);
			;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
		return result;

	}

	// COLLECTION REPORT
	@GetMapping("/collectionReport/")
	public List<Map<String, Object>> getCollectionReport(
			@RequestParam("collDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate collDate,
			@RequestParam("branchId") Integer branchId) {
		return collectionRepository.getCollectionReport(collDate, branchId);
	}

	// Disbursement status Update
	@PutMapping("/updateDisStatus/")
	public ResponseEntity<String> updateDisStatus(
			@RequestParam("disDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate disDate,
			@RequestParam long loanId) {
		Optional<LoanCreation> optionalLoan = loanCreationRepository.findByIdanddisDate((int) loanId);
//		 System.out.println("LOAN DETAILS = "+loans);
//	         = loanCreationRepository.findById(loanId);
		System.out.println(optionalLoan);

		if (optionalLoan.isPresent()) {
			LoanCreation loan = optionalLoan.get();
			Integer status = 2;
			loan.setDdate(disDate);
			loan.setStatus(status);
			loanCreationRepository.save(loan);
			return ResponseEntity.ok("Loan status updated successfully.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// GET CASE IN HAND DATA
	// get loanrepayment Data to collectiondate and branchId
	@GetMapping("/advancePaymentData/")
	public Map<String, Object> getAdvancePayment(
			@RequestParam("collectionDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate collectionDate,
			@RequestParam("branchId") Integer branchId) {
		System.out.println(collectionDate + ".......  and...........  " + branchId);

		Map<String, Object> results = new HashMap<>();

		Integer depositAmount = collectionRepository.getSumOfDepositAmmount(branchId, collectionDate);
		results.put("DepositAmount", depositAmount);

		Integer collectionAmount = collectionRepository.getSumOfCollectionAmmount(branchId, collectionDate);
		results.put("CollectionAmount", collectionAmount);

		Integer receivedAmount = collectionRepository.getSumOfReceivedAmount(branchId, collectionDate);
		results.put("receivedAmount", receivedAmount);

		List<Map<String, Object>> otherCollectionandDisbursementAmount = collectionRepository
				.getSumOfDisbursementAndOtherCollectionAmmount(branchId, collectionDate);
		results.put("OtherCollectionandDisbursementAmount", otherCollectionandDisbursementAmount);
		
		
		Integer openingAmount=collectionRepository.getSumOfOpeningAmount(branchId, collectionDate);
		results.put("openingAmount", openingAmount);
		
		
		
		return results;
	}

	// Save Case In Hand
	@PostMapping("/saveCaseInHand/")
	public Deposit saveCaseInHand(@RequestParam("branchId") Integer branchId, @RequestParam("bankId") Integer bankId,
			@RequestParam("amount") Double amount,
			@RequestParam("dcrDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dcrDate)
			throws Exception {

		// Retrieve the authentication object from the security context
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Get the user details from the authentication object
		User userDetails = (User) authentication.getPrincipal();
		// Get the current user's ID as a Long
		Integer userId = (int) userDetails.getId();
		// Get the current user's name
		String username = userDetails.getUsername();

		Deposit deposit = new Deposit();
		LocalDate currentDate = LocalDate.now();

		Date entryDate = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate.toString());
		Date dcrDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(dcrDate.toString());
		deposit.setEntryBy(userId);
		deposit.setAmount(amount);
		deposit.setDepositDate(entryDate);
		deposit.setDcrDate(dcrDate1);
		Integer accountId = bankId;
		long newbranchId = (long) branchId;
		Branch branch = branchRepository.getBranchById(newbranchId);
		System.out.println(branch);
		deposit.setAccountId(accountId);
		Account account = accountRepository.findById(accountId).orElse(null);
		if (account != null && branch != null) {
			deposit.setBankId(account.getBankId());
			deposit.setBankName(account.getBankName());
			deposit.setBankType(account.getBankType());
			deposit.setBranchId((int) branchId);
			deposit.setBranchName(branch.getName());
		} else {
			// Handle the case when the bank with the provided bankId is not found.
			throw new Exception("Bank not found for bankId: " + accountId);
		}
		depositRepository.save(deposit);

		return deposit;
	}
	
	
	
	
	@PostMapping("/payment_remove/")
	public ResponseEntity<String> paymentRemove(@RequestParam("loanId") Integer loanId,
	        @RequestParam("paymentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paymentDate,
	        @RequestParam("deletedBy") Integer deletedBy) {
		System.out.println(loanId+" "+  paymentDate +" "+ deletedBy);

		try {
	        collectionRepository.payment_remove(loanId, paymentDate, deletedBy);
	        return ResponseEntity.ok("{\"message\": \"Payment Removed Successfully!!\\nLoan Id: " + loanId + "\"}");
			} catch (Exception e) { 
				  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Failed to remove payment.\"}");
				}
	}

	
	
}
