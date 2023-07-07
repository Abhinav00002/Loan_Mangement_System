package com.lms.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
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
import com.lms.model.Customer;
import com.lms.model.Lead;
import com.lms.model.LoanCreation;
import com.lms.model.LoanRepayment;
import com.lms.model.Scheme;
import com.lms.model.address.Days;
import com.lms.model.address.Time;
import com.lms.repo.BranchRepository;
import com.lms.repo.CenterRepository;
import com.lms.repo.DaysRepository;
import com.lms.repo.LeadRepository;
import com.lms.repo.LoanCreationRepository;
import com.lms.repo.LoanRepaymentRepository;
import com.lms.repo.SchemeRepository;
import com.lms.repo.TimeRepository;
import com.lms.repo.customer.CustomerRepository; 



@RestController
@CrossOrigin("*")
@RequestMapping("/loanCreation")
public class LoanCreationController {

	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private BranchRepository branchRepository;
	@Autowired
	private TimeRepository timeRepository;
	@Autowired
	private DaysRepository daysRepository;
	@Autowired
	private LoanCreationRepository loanCreationRepository;
	@Autowired
	private SchemeRepository schemeRepository;
	@Autowired
	private LoanRepaymentRepository loanRepaymentRepository;
	@Autowired
	private CenterRepository centerRepository;
	
	
	
	@PostMapping("/")
	public LoanCreation createLoanCreation(@RequestBody LoanCreation loanCreation) throws Exception{
		Integer leadId=loanCreation.getLeadid();
		Integer	centerId=loanCreation.getCentername();
		Integer	branchId=loanCreation.getBranchname();
	 System.out.println("lead id ===== "+leadId);
		double financeamount=0;
		long schemeno =loanCreation.getScheme();
		//fetch Scheme By id
		Scheme scheme=schemeRepository.findById(schemeno); 
		double loanAmount=	scheme.getLoneamount();
		double interestRate=scheme.getIntrestrate();  
		int emitype = scheme.getEmitype();
		double emi=scheme.getEmi();
		double tenor=scheme.getTenor();
		double pfAmount=scheme.getPfamount();
		double insuranceAmount=scheme.getInsuranceamount();
		long schemeBy= scheme.getSchemeBy();
		double irr=scheme.getIrr();
	 
		
		
		
		
		LocalDate dueDate= loanCreation.getMeetingDate();
		System.out.println(dueDate);
		// get center date  
		 
		 
		
	 // Calculate interest for payment
        double  Interest = 0;
        double remainingAmount = loanAmount;
        List<LoanRepayment> liLoanRepayments=new ArrayList<LoanRepayment>();
        
        /*for loop started for interest calculation*/
        
        for (int i = 1; i <= tenor; i++) {
     	
       
        if(i>1) {
        			/*Four Nightly*/
			        if (emitype==3) {
			        		// Interest=(remainingAmount*interestRate/100*365)*7; 
			        		 dueDate=dueDate.plusDays(14);
			        }  /*Weekly EMI*/ 
			        else if (emitype==2) {
			        	// Interest=(remainingAmount*interestRate/100*365)*14;
			        	 dueDate=dueDate.plusDays(7);
			        }/* Monthly EMI */
			        else if (emitype==1) {
//			        	 Interest=(remainingAmount*interestRate/100*12);//monthly
			        	 dueDate=dueDate.plusMonths(1);
			        }
                }        
			        Interest=remainingAmount*irr;
			        double principal = emi - Interest ;
        	
          
           
           
            
            
            LoanRepayment loanRepayment=new LoanRepayment(); 
            loanRepayment.setLoanId(leadId);
            loanRepayment.setIntrest(Interest);
            loanRepayment.setEmi(emi);
            loanRepayment.setCollectionBy(schemeBy);
            loanRepayment.setPrinciple(principal);
            loanRepayment.setInstallmentNo(i);
//            loanRepayment.setCollectionDate(dueDate);
            loanRepayment.setDueDate(dueDate); 
            loanRepayment.setStatus(1);
            loanRepayment.setOpenningAmount(remainingAmount);
            loanRepayment.setBranchId(branchId);
            loanRepayment.setCenterId(centerId);
            liLoanRepayments.add(loanRepayment);
            
            remainingAmount =remainingAmount - principal;
        } /*for loop close*/ 
            
        
        loanRepaymentRepository.saveAll(liLoanRepayments);
     
      
       

		
		LoanCreation loan=loanCreationRepository.save(loanCreation);
		
		System.out.println(loan);
		return loan;
		
	
        }
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/list")
	public List<LoanCreation> getLoanCreations(Integer schemeno){
		List<LoanCreation> loanCreations=new ArrayList<LoanCreation>();
		loanCreations=loanCreationRepository.findAll();
		return loanCreations;
	}
	
	
	@GetMapping("/list/passbook/{loan_Id}")
	public List<LoanRepayment> getLoanRepaymentByloanId(@PathVariable ("loan_Id") Integer loan_Id){
		return loanRepaymentRepository.getLoanRepaymentByloanId(loan_Id);
	}
	
	
	@GetMapping("/list/loanrepayment")
	public List<LoanRepayment> findByDueDateAndBranchId(@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
	                                                    @RequestParam("branchId") Integer branchId) {
		System.out.println("Due Date: " + dueDate);
	    System.out.println("Branch ID: " + branchId);
	    return loanRepaymentRepository.findByDueDateAndBranchId(dueDate, branchId);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
		//	print PASSBOOK PDF 
			@GetMapping("/combined-data/{loanId}")
			public ResponseEntity<byte[]> getCombinedDataByLoanId(@PathVariable("loanId") Integer loanId) {
				    	 
				
				// Fetch the loan repayment data based on due date and branch ID
				List<LoanRepayment> loanRepayments = loanRepaymentRepository.getLoanRepaymentByloanId(loanId);
				// Create a list to hold the response data
				List<Map<String, Object>> responseData = new ArrayList<>();
				
				for (LoanRepayment loanRepayment : loanRepayments) {
				//retrive loan details
				List<LoanCreation> loan=loanCreationRepository.getLoanCreationByleadid( loanRepayment.getLoanId());
				// Retrieve lead details
				List<Lead> leads = leadRepository.findByleadID(loanRepayment.getLoanId());
				if (!leads.isEmpty()) {
				Lead lead = leads.get(0);
				
				// Retrieve borrower details
				List<Customer> borrowers = customerRepository.findBycid(lead.getBorrowerID());
				List<Customer> coBorrowers = customerRepository.findBycid(lead.getCoBorrowerId());
				
				// Retrieve center details
				List<Center> centers = centerRepository.getCenterByncid(lead.getCenterID());
				
				// Create a map to hold the data for this loan repayment
				Map<String, Object> loanRepaymentData = new HashMap<>();
				loanRepaymentData.put("loanRepayment", loanRepayment);
				loanRepaymentData.put("lead", lead);
				loanRepaymentData.put("borrowers", borrowers);
				loanRepaymentData.put("coBorrowers", coBorrowers);
				loanRepaymentData.put("centers", centers);
				loanRepaymentData.put("loan", loan);
				// Retrieve branch details
				// Retrieve branch details
				if (!centers.isEmpty()) {
				    Center centerData = centers.get(0); // Assuming there is only one center
				    Long centerBranchId = centerData.getBname();
				    Branch branch = branchRepository.findById(centerBranchId).orElse(null);
				    loanRepaymentData.put("branch", branch); 

				// Retrieve time details
				List<Time> times = timeRepository.getTimeBytid(centerData.getTime());
				loanRepaymentData.put("times", times);
				
				// Retrieve day details
				List<Days> days = daysRepository.getDayBydid(centerData.getCmday());
				loanRepaymentData.put("days", days);
				}
				
				// Add the data for this loan repayment to the response list
				responseData.add(loanRepaymentData);
				}
				}
				//
//				Optional<Branch> branch = branchRepository.findById(Long.valueOf(branchId));
//				String branchName = branch.map(Branch::getName).orElse("");
				// Generate the PDF document
				Document document = new Document();
				
				try {
					
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, baos);
				// Create a custom footer event handler
		        PdfPageEventHelper eventHandler = new PdfPageEventHelper() {
		            public void onEndPage(PdfWriter writer, Document document) {
		                // Create a paragraph for the footer text
//		                Paragraph footer = new Paragraph("\"MEXWELL MICRO FOUNDATION\\nRegistered Office: Dhiraj Singh Nabada, Bulandshar Uttar Pradesh 203203 IN\\nEmail:mexwellmicrofoundation@gmail.com\",\r\n");
//		                footer.setAlignment(Element.ALIGN_CENTER);
		                
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
		                float maxTextWidth = Math.max(
		                        canvas.getEffectiveStringWidth(footerLine1, false),
		                        Math.max(
		                            canvas.getEffectiveStringWidth(footerLine2, false),
		                            canvas.getEffectiveStringWidth(footerLine3, false)
		                        )
		                    );

		                // Calculate the x-position to center the text
		                float x = (document.right() + document.left() - maxTextWidth) / 2;
		                float y = document.bottomMargin(); // Set the y-position to the bottom margin of the page

		                // Show each line of the footer text separately
		                canvas.showTextAligned(Element.ALIGN_LEFT, footerLine1, x+100, y, 0);
		                canvas.showTextAligned(Element.ALIGN_LEFT, footerLine2, x, y - 12, 0); // Adjust the vertical position as needed
		                canvas.showTextAligned(Element.ALIGN_LEFT, footerLine3, x+90, y - 24, 0); // Adjust the vertical position as needed

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
				PdfPTable table = new PdfPTable(9);
				float[] columnWidths = {2f, 4f, 2.5f, 3.8f, 3.5f, 4f, 4f, 4.5f, 	4.5f };
				table.setWidths(columnWidths);
				table.setWidthPercentage(100);
				table.setSpacingAfter(10);
				table.setSpacingBefore(50);
				table.setTotalWidth(530);
				//table.setSpacingBefore(10f);
				//table.setSpacingAfter(10f);
				 
				// Set table header style
				Font headerFont =new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD, new BaseColor(0, 0, 0));
				Font cellFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL, new BaseColor(0, 0, 0));
				
				// Add the header row only once
	            boolean headerAdded = false;

	            // Add table rows
	            int serialNumber = 1;
	            for (Map<String, Object> data : responseData) {
	                LoanRepayment loanRepayment = (LoanRepayment) data.get("loanRepayment");
	                Lead lead = (Lead) data.get("lead");
	                List<Customer> borrowers = (List<Customer>) data.get("borrowers");
	                List<Customer> coBorrowers = (List<Customer>) data.get("coBorrowers");
	                List<Days> day=(List<Days>) data.get("days");
	                List<Time> time=(List<Time>) data.get("times");
	                List<Center> center=(List<Center>)data.get("centers");
	                List<LoanCreation> loan=(List<LoanCreation>)data.get("loan");
	                String borrowerName = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getCname() : "";
	                String coBorrowerName = (coBorrowers != null && !coBorrowers.isEmpty()) ? coBorrowers.get(0).getCname() : "";
	                String address = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getAddl1() : "";
	                String address1 = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getAddl2() : "";
	                String mobileNo = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getMobnumber() : "";
	                String Day=day.get(0).getDname();
	                	 
	                Integer loanID = loanRepayment.getLoanId();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	                String formattedDueDate = loanRepayment.getDueDate().format(formatter);
	                String disbursementDate= loan.get(0).getDdate().format(formatter);
	                 // Add the header row if it hasn't been added yet
	                if (!headerAdded) {
	             
				PdfPCell headerCell = new PdfPCell();
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setNoWrap(true);
				headerCell.setMinimumHeight(20f);
				//headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				//headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				//headerCell.setPadding(5f);
				//headerCell.setColspan(9); // Set colspan to span 7 columns
				// Create a table for the header text and date
				//Set table headers
				//Row 1: Image
				 
				headerCell.setRowspan(4);
				headerCell.setColspan(3);
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setMinimumHeight(20f);
				table.addCell(headerCell);
				
				//Add more cells here based on your requirements
				
				//Row 2: Id, Branch, Meeting Day
				headerCell = new PdfPCell(new Phrase("Loan Id: "+loanID , headerFont));
				headerCell.setColspan(2);
				table.addCell(headerCell);
				Branch headerBranch = (Branch) data.get("branch");
				 String headerBranchName = headerBranch.getName();
				headerCell = new PdfPCell(new Phrase("Branch: " + headerBranchName, headerFont));
				headerCell.setColspan(2);
				table.addCell(headerCell);
				headerCell = new PdfPCell(new Phrase("Meeting Day: " +Day, headerFont));
				headerCell.setColspan(2);
				table.addCell(headerCell);
				
				//Add more cells here based on your requirements
				
				//Row 3: Name, Spouse, Manage By
				headerCell = new PdfPCell(new Phrase("Name: "+borrowerName, headerFont));
				headerCell.setColspan(2);
				table.addCell(headerCell);
				headerCell = new PdfPCell(new Phrase("Spouse: "+coBorrowerName  , headerFont));
				headerCell.setColspan(2);
				table.addCell(headerCell);
				headerCell = new PdfPCell(new Phrase("Manage By: ", headerFont));
				headerCell.setColspan(2);
				table.addCell(headerCell);
				
				//Add more cells here based on your requirements
				
				//Row 4: Finance Amt, Disbursement Date, Net Disbursement
				headerCell = new PdfPCell(new Phrase("Finance Amt: " +  ((int)loanRepayment.getOpenningAmount()), headerFont));
				headerCell.setColspan(2);
				headerCell.setRowspan(1);
				table.addCell(headerCell);
				PdfPCell cell2 = new PdfPCell();
				cell2.addElement(new Phrase("Disbursement Date: "+disbursementDate , headerFont));
				//cell.addElement(new Phrase(loanData.loan[0].ddate, subHeaderFont));
				cell2.addElement(new Phrase("Mobile No: " + mobileNo  , headerFont));
				//cell.addElement(new Phrase(borrowerData.borrower[0].mobnumber, subHeaderFont));
				cell2.setColspan(2);
				table.addCell(cell2);
				//Row 2: Net Disbursement, Center No, Time
				PdfPCell cell1 = new PdfPCell();
				cell1.addElement(new Phrase("Net Disbursement: " +(int)loanRepayment.getOpenningAmount() , headerFont));
				//cell.addElement(new Phrase(passbookData[0].openningAmount, subHeaderFont));
				
				//Create a Phrase with both "Time" and "Center No" values
				String timeAndCenterNo = "Center No: "+center.get(0).getNcid()+"    Time:"+time.get(0).getTime()+"AM    " ;
				cell1.addElement(new Phrase(timeAndCenterNo, headerFont));
				
				cell1.setColspan(2);
				cell1.setRowspan(2);
				table.addCell(cell1);;
				 
				
				
				
				
				//Add more cells here based on your requirements
				
				//Row 5: Client Address
				headerCell = new PdfPCell(new Phrase("Client Address: " +address+" "+address1 , headerFont));
				headerCell.setColspan(6);
				table.addCell(headerCell);
				
				 
				
				
					 
				// Set remaining header cells
				headerCell = new PdfPCell();
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				//headerCell.setNoWrap(true);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
				headerCell = new PdfPCell(new Phrase("Inst. NO.", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("Due Date", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("EMI", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("Openning", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("Principle", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("Interest", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("BOP", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("Receipt Date", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				
				headerCell = new PdfPCell(new Phrase("Receipt By", headerFont));
				headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				headerCell.setPadding(5f);
				table.addCell(headerCell);
				 
				  headerAdded = true; // Set the flag to true
	                }
				// Add table rows
				  
				// Add table row
				PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(loanRepayment.getInstallmentNo()), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(formattedDueDate), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf((int)Math.ceil(loanRepayment.getEmi())), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf((int)Math.ceil(loanRepayment.getOpenningAmount())), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf((int)Math.ceil(loanRepayment.getPrinciple())), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase (String.valueOf((int)Math.ceil(loanRepayment.getIntrest())), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf((int)Math.ceil((loanRepayment.getOpenningAmount()-loanRepayment.getPrinciple()))), cellFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				;
				table.addCell("");
				table.addCell("");
				 
				
				serialNumber++;
				}
				
				// Add the table to the document
				 
				 document.add(table);
				//Add signatures and footer content
				Paragraph signature = new Paragraph("BM Signature",headerFont);
				signature.setAlignment(Element.ALIGN_LEFT);
				signature.setSpacingAfter(80);
				signature.setSpacingBefore(20);
				document.add(signature);
				
				Paragraph borrowerSignature = new Paragraph("Borrower Signature", headerFont);
				borrowerSignature.setAlignment(Element.ALIGN_LEFT); 
				
				
				Paragraph coBorrowerSignature = new Paragraph("Co-Borrower Signature",headerFont);
				coBorrowerSignature.setAlignment(Element.ALIGN_RIGHT); 
				coBorrowerSignature.setSpacingAfter(0);
				document.add(coBorrowerSignature);
				document.add(borrowerSignature);
				
			 
				
				
				document.close();
				
				// Set the response headers
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_PDF);
		        headers.setContentDisposition(ContentDisposition.attachment()
		                .filename(loanId +"_passbook.pdf")
		                .build());
				
				// Return the PDF content as ResponseEntity
				return ResponseEntity.ok()
				.headers(headers)
				.contentLength(baos.size())
				.body(baos.toByteArray());
				
	} catch (DocumentException e) {
				e.printStackTrace();
				
		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		// Return an error response if there was an exception
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}




} 