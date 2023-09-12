package com.lms.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.lms.model.Branch;
import com.lms.model.Center;
import com.lms.model.Collection;
import com.lms.model.CollectionProcedure;
import com.lms.model.Customer;
import com.lms.model.Lead;
import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;
import com.lms.model.Scheme;
import com.lms.model.User;
import com.lms.model.address.Days;
import com.lms.model.address.Time;
import com.lms.repo.BranchRepository;
import com.lms.repo.CenterRepository;
import com.lms.repo.CollectionProcedureRepository;
import com.lms.repo.CollectionRepository;
import com.lms.repo.DaysRepository;
import com.lms.repo.LeadRepository;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.LoanRepaymentRepository;
import com.lms.repo.SchemeRepository;
import com.lms.repo.TimeRepository;
import com.lms.repo.UserRepository;
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

	// Get Collection Data
	@GetMapping("/collection-data/")
	public ResponseEntity<List<Map<String, Object>>> getData(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId) {

		// Fetch the loan repayment data based on due date and branch ID
		List<Map<String, Object>> loanRepayments = loanRepaymentRepository.findBydueDateAndBranchId(dueDate, branchId);

		return ResponseEntity.ok(loanRepayments);
	}

	// Print Pdf of Collection Data (CDS Print)
	@PostMapping("/collection-data/print-pdf/")
	public ResponseEntity<byte[]> postDataAndGeneratePDF(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId) throws MalformedURLException, IOException {

		// Fetch the loan repayment data based on due date and branch ID
		List<Map<String, Object>> loanRepayments = loanRepaymentRepository.findBydueDateAndBranchId(dueDate, branchId);

		// Generate the PDF document
		Document document = new Document();

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, baos);
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
																							// position as needed
					canvas.showTextAligned(Element.ALIGN_LEFT, footerLine3, x + 90, y - 24, 0); // Adjust the vertical
																								// position as needed

					canvas.endText();
				}
				// ...
			};

			// Set the footer event handler for the writer
			writer.setPageEvent(eventHandler);

			document.open();

			// Add the logo at the top of the page

			Image logoImage = Image.getInstance("vitarma.jpeg");
			logoImage.scaleToFit(100, 100);
			logoImage.setAlignment(Element.ALIGN_CENTER);
			logoImage.setBackgroundColor(BaseColor.WHITE);
			document.add(logoImage);

			// Add space between the logo and the table
			document.add(Chunk.NEWLINE);

			// Create a table with 8 columns
			PdfPTable table = new PdfPTable(10);
			float[] columnWidths = { 1.3f, 2f, 3f, 3.5f, 3.5f, 8f, 1.5f, 3f, 3f, 3f };
			table.setWidths(columnWidths);
			table.setWidthPercentage(100);
			table.setSpacingAfter(10);
			table.setSpacingBefore(10);
			table.setTotalWidth(530);
			// table.setSpacingBefore(10f);
			// table.setSpacingAfter(10f);

			// Set table header style
			Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD, new BaseColor(0, 0, 0));
			Font cellFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL, new BaseColor(0, 0, 0));

			// Add the header row only once
			boolean headerAdded = false;

			int serialNumber = 1;
			int totalCollection = 0;
			for (Map<String, Object> collectionData : loanRepayments) {
				String branchName = (String) collectionData.get("branchName");
				Double EMI = (Double) collectionData.get("emi");

				// Add the header row if it hasn't been added yet
				if (!headerAdded) {
					PdfPCell headerCell = new PdfPCell();
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setNoWrap(true);
					headerCell.setMinimumHeight(20f);
					// headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					// headerCell.setPadding(5f);
					headerCell.setColspan(10); // Set colspan to span 7 columns
					// Create a table for the header text and date
					PdfPTable headerTable = new PdfPTable(1);

					// Set the total width percentage of the table (optional)
					headerTable.setWidthPercentage(100);

					// Create the cell for the header text
					PdfPCell headerTextCell = new PdfPCell(
							new Phrase("Collection Day Sheet " + dueDate + " and Branch " + branchName, headerFont));
					headerTextCell.setColspan(10); // Set the colspan to span all columns
					headerTextCell.setBorder(Rectangle.OUT_BOTTOM);
					headerTable.addCell(headerTextCell);

					// Add the header table to the header cell
					headerCell.addElement(headerTable);

					// Add the header cell to the first column of the table
					table.addCell(headerCell);

					// Set remaining header cells
					headerCell = new PdfPCell();
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					// headerCell.setNoWrap(true);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPaddingBottom(10f); // Add padding at the bottom of the cell
					headerCell.setPaddingLeft(10f); // Add padding to the left of the cell
					headerCell.setNoWrap(true); // Set NoWrap property to false
					headerCell.setUseAscender(true); // Enable Ascender property
					headerCell.setUseDescender(true); // Enable Descender property
					headerCell.setFixedHeight(30f); // Set a fixed height for the cell
					headerCell.setLeading(12f, 0f); // Set the leading of the font

					headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					headerCell = new PdfPCell(new Phrase("S. NO.", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Loan Id", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Borrower Name", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Co Borrower Name", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Mobile No", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Address", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("EMI", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("Client Sign", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("OD Installment", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);

					headerCell = new PdfPCell(new Phrase("OD Ammount", headerFont));
					headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
					headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					headerCell.setPadding(5f);
					table.addCell(headerCell);
					;
					headerAdded = true; // Set the flag to true
				}

				// Add table rows

				// Calculate total collection
				totalCollection += EMI;

				// Add table row
				PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(serialNumber), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(collectionData.get("loanId")), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(collectionData.get("borrowerName")), cellFont));
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(collectionData.get("CoBorrowerName")), cellFont));
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(collectionData.get("borrowerContact")), cellFont));
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(
						String.valueOf(collectionData.get("borrowerAdd1") + " " + collectionData.get("borrowerAdd2")),
						cellFont));
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf((int) Math.floor(EMI)), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				table.addCell("");
				table.addCell("");
				table.addCell("");

				serialNumber++;
			}

			// Add the table to the document
			// Add total collection row
			PdfPCell totalCell = new PdfPCell(new Phrase("Total Collection:", headerFont));
			totalCell.setColspan(7);
			totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			// totalCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(totalCell);

			// Create a new row for the total collection
			PdfPRow totalCollectionRow = new PdfPRow(
					new PdfPCell[] { new PdfPCell(new Phrase("Total Collection:", headerFont)), // Cell with "Total
																								// Collection:" label
							new PdfPCell(), new PdfPCell(), new PdfPCell(), new PdfPCell(), new PdfPCell(),
							new PdfPCell(new Phrase(String.valueOf(totalCollection), cellFont)), // Cell with the total
																									// collection value
							new PdfPCell(), new PdfPCell(), new PdfPCell(), });
			// Set the colspan for the total collection cell
			totalCollectionRow.getCells()[0].setColspan(6);
			// Add the total collection row to the table
			table.getRows().add(totalCollectionRow);

			document.add(table);

			document.close();

			// Set the response headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "data.pdf");

			// Return the PDF content as ResponseEntity
			return ResponseEntity.ok().headers(headers).contentLength(baos.size()).body(baos.toByteArray());

		} catch (DocumentException e) {
			e.printStackTrace();

		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
				if (pendAmount == null || due == null || dueEmi == null) {
					// Handle the error or log it
					Map<String, Object> errorResult = new HashMap<>();
					errorResult.put("error", "One or more required values are missing for loanId: " + loanId);
					results.add(errorResult);
					continue; // Skip to the next item
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
	@GetMapping("/advance_pament/")
	@Transactional
	@JsonIgnore
	public Integer advancePaymentData(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId, @RequestParam("emi") double emi,
			@RequestParam("loanId") Integer loanId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userDeails = (User) authentication.getPrincipal();
		Integer userId = (int) userDeails.getId();
		Integer result;
		try {
			result = collectionProcedureRepository.upsUpdateCollection(loanId, dueDate, emi, 1, userId, branchId);
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


	
	//Get Sum of Collection For Case in hand
	@GetMapping("/sumCollection/")
	public List<Map<String, Object>> getSumOfCollection(
			@RequestParam("collectionDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate collectionDate,
			@RequestParam("branchId") Integer branchId){
		List<Map<String, Object>> sumOfCollection=collectionRepository.getSumOfCollectionAmmount(branchId, collectionDate);
		System.out.println("Sum Of Collection: "+sumOfCollection);
		return sumOfCollection;
	}

}
