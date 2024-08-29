package com.lms.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.lms.dto.repository.LoanDTORepository;
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
import com.lms.service.impl.UserServiceImpl;

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
	@Autowired
	private UserServiceImpl userServiceImpl;

	// Get Collection Data
	@GetMapping("/collection-data/")
	public ResponseEntity<List<PrintCDS>> getData(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId, Principal principal) {

		String username = principal.getName();
		Integer branchId1 = userServiceImpl.getUserBranchId(username);
		int userRank = userServiceImpl.getUserRank(username);
		System.out.println("branchid : " + branchId1);
		System.out.println("RRank : " + userRank);
		if (userRank == 1 && branchId1 == 1) {
			branchId1 = branchId;
		}

		if (!userServiceImpl.isUserAuthorized(username, userRank, branchId1)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		// Fetch the loan repayment data based on due date and branch ID
		List<PrintCDS> cdsData = printCDSRepository.getCDSData(dueDate, branchId1);
		for (PrintCDS Data : cdsData) {
			System.out.println("CDSDATA:" + Data);
		}
		return ResponseEntity.ok(cdsData);
	}

	@RequestMapping(value = { "/CDS/" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getByteTC(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId, Principal principal)
			throws IOException, DocumentException, ParseException {

		String username = principal.getName();
		Integer branchId1 = userServiceImpl.getUserBranchId(username);
		int userRank = userServiceImpl.getUserRank(username);
		System.out.println("branchid : " + branchId1);
		System.out.println("RRank : " + userRank);
		if (userRank == 1 && branchId1 == 1) {
			branchId1 = branchId;
		}

		if (!userServiceImpl.isUserAuthorized(username, userRank, branchId1)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		List<PrintCDS> cdsData = printCDSRepository.getCDSData(dueDate, branchId1);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter writer = PdfWriter.getInstance(document, out);

			// Add header and footer
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			writer.setPageEvent(event);

			document.open();
			PdfPTable table = createPdfTable();
			int totalCollection = 0;
			int previousCenterId = -1;
			int previousCenterType = -1;
			Paragraph space = new Paragraph();
			space.setSpacingBefore(10f);
			document.add(space);

//    addColumnHeaders(table,printHeader);

			int serialNumber = 1;
			for (int i = 0; i < cdsData.size(); i++) {
				PrintCDS collectionData = cdsData.get(i);
				if (i == 0) {
					addColumnHeaders(table, collectionData);
				}
				if (collectionData.getCenterid() != previousCenterId
						|| collectionData.getCenterType() != previousCenterType) {
					if (previousCenterId != -1) {
						addTotalRow(table, totalCollection, collectionData);
						document.add(table);
						document.newPage();
						space.setSpacingAfter(10f);
						document.add(space);

						table = createPdfTable();
						if (i < cdsData.size()) {
							addColumnHeaders(table, collectionData);
						}

						serialNumber = 1;
					}
					totalCollection = 0;
					previousCenterId = collectionData.getCenterid();
					previousCenterType = collectionData.getCenterType();
				}
				addTableRow(table, collectionData, serialNumber++);
				totalCollection += collectionData.getEmi();
			}

			addTotalRow(table, totalCollection, cdsData.get(cdsData.size() - 1));
			document.add(table);

			document.close();

			ByteArrayInputStream bis = new ByteArrayInputStream(out.toByteArray());
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=" + branchId + ".pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	private PdfPTable createPdfTable() throws DocumentException {
		PdfPTable table = new PdfPTable(12);
		float[] columnWidths = { 1f, 1.5f, 3f, 3f, 3f, 2f, 5f, 1f, 1f, 1f, 1f, 5f };
		table.setWidths(columnWidths);
		table.setWidthPercentage(100);
		table.setSpacingAfter(10);
		table.setSpacingBefore(50);
		table.setTotalWidth(530);
		return table;
	}

	class HeaderFooterPageEvent extends PdfPageEventHelper {
		public void onStartPage(PdfWriter writer, Document document) {
			try {
				// Add logo
				Image logo = Image.getInstance("vitarma.jpeg");
				logo.scaleToFit(100, 100);
				logo.setAlignment(Element.ALIGN_CENTER);
				logo.setBackgroundColor(BaseColor.WHITE);

				float logoWidth = logo.getScaledWidth();
				float logoHeight = logo.getScaledHeight();
				float pageWidth = document.right() - document.left();
				float x1 = (pageWidth - logoWidth) / 2;
				float y1 = document.top() - 30;

				logo.setAbsolutePosition(x1, y1);
				document.add(logo);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void onEndPage(PdfWriter writer, Document document) {
			try {
				Font footerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);

				PdfContentByte canvas = writer.getDirectContent();
				canvas.beginText();
				try {
					canvas.setFontAndSize(BaseFont.createFont(), 10);
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				String footerLine1 = "MEXWELL MICRO FOUNDATION";
				String footerLine2 = "Registered Office: Dhiraj Singh Nabada, Bulandshar Uttar Pradesh 203203 ";
				String footerLine3 = "Email: mexwellmicrofoundation@gmail.com";

				float maxTextWidth = Math.max(canvas.getEffectiveStringWidth(footerLine1, false),
						Math.max(canvas.getEffectiveStringWidth(footerLine2, false),
								canvas.getEffectiveStringWidth(footerLine3, false)));

				float x = (document.right() + document.left() - maxTextWidth) / 2;
				float y = document.bottomMargin();

				canvas.setFontAndSize(footerFont.getBaseFont(), footerFont.getSize());

				canvas.showTextAligned(Element.ALIGN_LEFT, footerLine1, x + 100, y, 0);
				canvas.showTextAligned(Element.ALIGN_LEFT, footerLine2, x + 50, y - 12, 0);
				canvas.showTextAligned(Element.ALIGN_LEFT, footerLine3, x + 90, y - 24, 0);

				canvas.endText();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addColumnHeaders(PdfPTable table, PrintCDS collectionData) {
		Integer EMI = collectionData.getEmi();
		Integer centerId = collectionData.getCenterid();
		String branchName = collectionData.getBranchName();
		String day = collectionData.getDaysName();
		float time = collectionData.getTime();
		int centerType = collectionData.getCenterType();
		String staffName = collectionData.getStaffName();
		Date date1 = collectionData.getDuedate();
		String printDate = new SimpleDateFormat("dd-MM-yy").format(date1);

		PdfPCell cell;
		String printHeader = "";
		if (collectionData.getCenterType() == 1) {
			printHeader = "CDS " + branchName + " as on " + printDate + " Day " + day;
		} else {
			printHeader = "CDS " + branchName + " as on " + printDate + " Centre No " + centerId + " Day " + day
					+ " Time " + time + " AM " + " RO: " + staffName;
		}
		Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
		cell = new PdfPCell(new Phrase(printHeader, headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		cell.setColspan(12);
		table.addCell(cell);

		// Serial Number
		cell = new PdfPCell(new Phrase("S. NO.", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Loan Id
		cell = new PdfPCell(new Phrase("Loan Id", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// RO (if applicable)
		if (collectionData.getCenterType() == 1) {
			cell = new PdfPCell(new Phrase("RO", headerFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setPadding(5f);
			table.addCell(cell);
		}

		// Borrower Name
		cell = new PdfPCell(new Phrase("Borrower Name", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Co Borrower Name
		cell = new PdfPCell(new Phrase("Co Borrower Name", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Mobile Number
		cell = new PdfPCell(new Phrase("Mobile No", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Address
		cell = new PdfPCell(new Phrase("Address", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Due Amount
		cell = new PdfPCell(new Phrase("Due Amt.", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Total Instalments
		cell = new PdfPCell(new Phrase("Total INST.", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Pending Instalments
		cell = new PdfPCell(new Phrase("P. INST.", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Pending Amount
		cell = new PdfPCell(new Phrase("P. Amt.", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);
		table.addCell(cell);

		// Client Sign (spanning two columns for certain center types)
		cell = new PdfPCell(new Phrase("Client Sign", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPadding(5f);

		if (collectionData.getCenterType() != 1) {
			cell.setColspan(2);
			table.addCell(cell);
		} else {
			table.addCell(cell);
		}
	}

	private void addTableRow(PdfPTable table, PrintCDS collectionData, int serialNumber) {
		PdfPCell cell;

		Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);

		cell = new PdfPCell(new Phrase(String.valueOf(serialNumber), cellFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getLoanId()), cellFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		if (collectionData.getCenterType() == 1) {
			cell = new PdfPCell(new Phrase(collectionData.getStaffName(), cellFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
		} else {

		}

		cell = new PdfPCell(new Phrase(collectionData.getCustomerName(), cellFont));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(collectionData.getCoBorrowerName(), cellFont));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(collectionData.getMobileNumber(), cellFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(
				new Phrase(collectionData.getAddressLine1() + " " + collectionData.getAddressLine2(), cellFont));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.valueOf(collectionData.getEmi()), cellFont));
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

		if (collectionData.getCenterType() != 1) {
			PdfPCell mergedCell = new PdfPCell();
			mergedCell.setColspan(2);
			table.addCell(mergedCell);
		} else {
			table.addCell("");
		}
	}

	private void addTotalRow(PdfPTable table, int totalCollection, PrintCDS collectionData) {

		Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
		Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);

		PdfPCell totalCell = new PdfPCell(new Phrase("Total Collection:", headerFont));
		totalCell.setColspan(collectionData.getCenterType() != 1 ? 7 : 6);
		totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(totalCell);

		PdfPCell totalCollectionCell = new PdfPCell(new Phrase(String.valueOf(totalCollection), cellFont));
		totalCollectionCell.setColspan(5);
		table.addCell(totalCollectionCell);
	}

	// Collection Update (CDS Update)
	@PostMapping("/cds-update/")
	@Transactional
	public List<Map<String, Object>> postData(@RequestBody Map<String, Object> collectionData) {

		System.out.println("Collection DATA = " + collectionData);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userDetails = (User) authentication.getPrincipal();
		Integer userId = (int) userDetails.getId();

		Integer result=null;
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

				Integer pendAmount = collectionRepository.getPendingAmounttoLoanId(loanId);
				System.out.println("PENDING AMOUNT : " + pendAmount);

				Integer due = collectionRepository.getInstallmentNoToLoanId(loanId);
				System.out.println("INSTALLMENT NO : " + due);
				
				Integer dueEmi = collectionRepository.getEMIToLoanIdAndInstaNo(loanId, due);
				System.out.println("EMI : " + dueEmi);

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
				Integer differenceAmount=0;
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
						differenceAmount = netCollection;
					}

					 result = collectionProcedureRepository.upsUpdateCollection(loanId, dueDate, collectedAmount,
							1, userId, branchId);
					System.out.println("RESULT = " + result);

					Map<String, Object> resultMap = new HashMap<>();

					resultMap.put("result", result);
					results.add(resultMap);

					netCollection = netCollection - collectedAmount;

				} while (netCollection > 0);
					Collection updateCollection = new Collection();
					updateCollection.setLoanId(loanId);
					updateCollection.setCollDate(dueDate);
					updateCollection.setCollAmmount(collectionAmount);
					updateCollection.setCollType(1);
					updateCollection.setCollBy(userId);
					updateCollection.setCollStatus(1);
					updateCollection.setCollBranchId(branchId);
					updateCollection.setCollUpadateRepayment(2);
					updateCollection.setPendingAmount(differenceAmount);
					collectionRepository.save(updateCollection);
					result=updateCollection.getId();
//				collectionProcedureRepository.updateCollection(loanId, dueDate, collectionAmount , differenceAmount , "1", branchId, userId);
			}
		} catch (IndexOutOfBoundsException e) {

			e.printStackTrace();
			return null;
		}
		return results;
	}

	@GetMapping("/list/")
	public ResponseEntity<List<Map<String, Object>>> findBytoDateTofromDate(
			@RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			Principal principal) {

		String username = principal.getName();
		Integer userBranchId = userServiceImpl.getUserBranchId(username);
		int userRank = userServiceImpl.getUserRank(username);
		System.out.println("User Branch Id: " + userBranchId);
		System.out.println("User Rank: " + userRank);

		if (!userServiceImpl.isUserAuthorized(username, userRank, userBranchId)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		if (userRank == 1 && userBranchId == 1) {
			List<Map<String, Object>> loan = loanCreationRepository.findByddateBetween(toDate, fromDate);
			return ResponseEntity.ok(loan);
		}

		if (userRank == 2 || (userBranchId != 1 && userRank == 1)) {
			List<Map<String, Object>> loan = loanCreationRepository.findByBranchIdAndDdateBetween(userBranchId, toDate,
					fromDate);
			return ResponseEntity.ok(loan);
		}

		// If user rank is 3, they are not permitted to access disbursement data
		if (userRank == 3) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		// For any other cases, return forbidden status
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	}

	// Advance Payment
	@PostMapping("/advance_payment/")
	@Transactional
	public Integer advancePaymentData(
			@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
			@RequestParam("branchId") Integer branchId, @RequestParam("emi") double collectionAmount,
			@RequestParam("loanId") Integer loanId, @RequestParam("collectionBy") Integer userId) {

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
			Integer differenceAmount=0;
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
					differenceAmount = netCollection;
				}

				result = collectionProcedureRepository.upsUpdateCollection(loanId, dueDate, collectedAmount, 1, userId,
						branchId);
				System.out.println("RESULT = " + result);

				netCollection = netCollection - collectedAmount;

			} while (netCollection > 0);
			Collection updateCollection = new Collection();
			updateCollection.setLoanId(loanId);
			updateCollection.setCollDate(dueDate);
			updateCollection.setCollAmmount(collectionAmount);
			updateCollection.setCollType(1);
			updateCollection.setCollBy(userId);
			updateCollection.setCollStatus(1);
			updateCollection.setCollBranchId(branchId);
			updateCollection.setCollUpadateRepayment(2);
			updateCollection.setPendingAmount(differenceAmount);
			collectionRepository.save(updateCollection);
		    result=updateCollection.getId();
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

	@PutMapping("/updateDisStatus/")
	public ResponseEntity<String> updateDisStatus(
			@RequestParam("disDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate disDate,
			@RequestParam long loanId, @RequestParam int status) {

		Optional<LoanCreation> optionalLoan = loanCreationRepository.findByIdanddisDate((int) loanId);

		if (optionalLoan.isPresent()) {
			LoanCreation loan = optionalLoan.get();

			// Set common fields
			loan.setDdate(disDate);
			loan.setStatus(status);

			String message;
			switch (status) {
			case 2:
				message = "Loan Approved successfully.";
				break;
			case 3:
				message = "Loan is Hold.";
				break;
			case 4:
				message = "Loan Reject successfully.";
				break;
			default:
				return ResponseEntity.badRequest().body("Invalid status provided.");
			}

			loanCreationRepository.save(loan);

			return ResponseEntity.ok(message);
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

		Integer openingAmount = collectionRepository.getSumOfOpeningAmount(branchId, collectionDate);
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
		System.out.println(loanId + " " + paymentDate + " " + deletedBy);

		try {
			collectionRepository.payment_remove(loanId, paymentDate, deletedBy);
			return ResponseEntity.ok("{\"message\": \"Payment Removed Successfully!!\\nLoan Id: " + loanId + "\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("{\"error\": \"Failed to remove payment.\"}");
		}
	}

}
