package com.lms.controller;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lms.model.Branch;
import com.lms.model.Center;
import com.lms.model.Collection;
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
	@ Autowired
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
	
	
	
					
					@GetMapping("/collection-data/") 
					public ResponseEntity<List<Map<String, Object>>> getData(@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
				            @RequestParam("branchId") Integer branchId) {
				
				// Fetch the loan repayment data based on due date and branch ID
				List<LoanRepayment> loanRepayments = loanRepaymentRepository.findByDueDateAndBranchId(dueDate, branchId);
				
				// Create a list to hold the response data
				List<Map<String, Object>> responseData = new ArrayList<>();
				
				for (LoanRepayment loanRepayment : loanRepayments) {
				// Retrieve loan details
				List<LoanCreation> loan = loanCreationRepository.getLoanCreationByleadid(loanRepayment.getLoanId());
				
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
				Optional<Branch> branch = branchRepository.findById(Long.valueOf(branchId));
				loanRepaymentData.put("branch", branch.orElse(null));
				
				// Retrieve time details
				Integer timeId = centers.get(0).getTime();
				List<Time> times = timeRepository.getTimeBytid(timeId);
				loanRepaymentData.put("times", times);
				
				// Retrieve day details
				Integer dayId = centers.get(0).getCmday();
				List<Days> days = daysRepository.getDayBydid(dayId);
				loanRepaymentData.put("days", days);
				
				// Add the data for this loan repayment to the response list
				responseData.add(loanRepaymentData);
				}
				}
				
				// Return the response data as ResponseEntity
				return ResponseEntity.ok(responseData);
				}

	 
	  
	
	
	
	
	//Print Pdf of Collection Data (CDS Print)
	@PostMapping("/collection-data/print-pdf/")
	public ResponseEntity<byte[]> postDataAndGeneratePDF(@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
	                                                    @RequestParam("branchId") Integer branchId) {

	    // Fetch the loan repayment data based on due date and branch ID
	    List<LoanRepayment> loanRepayments = loanRepaymentRepository.findByDueDateAndBranchId(dueDate, branchId);

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
                if (!centers.isEmpty()) {
                    Center centerData = centers.get(0); // Assuming there is only one center
                    Long centerBranchId = centerData.getBname();
                    Optional<Branch> branch = branchRepository.findById(centerBranchId);
                    loanRepaymentData.put("branch", branch.orElse(null));

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
        
        Optional<Branch> branch = branchRepository.findById(Long.valueOf(branchId));
        String branchName = branch.map(Branch::getName).orElse("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDueDate = dueDate.format(formatter);
     // Generate the PDF document
        Document document = new Document();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();

            // Create a table with 8 columns
            PdfPTable table = new PdfPTable(10);
            float[] columnWidths = {1.3f, 2f, 3f, 3.5f, 3.5f, 8f, 1.5f, 3f, 3f, 3f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.setSpacingAfter(10);
			table.setSpacingBefore(10);
			table.setTotalWidth(530);
//            table.setSpacingBefore(10f);
//            table.setSpacingAfter(10f);

            // Set table header style
            Font headerFont =new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD, new BaseColor(0, 0, 0));
            Font cellFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL, new BaseColor(0, 0, 0));
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setNoWrap(true);
            headerCell.setMinimumHeight(20f);
//            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            headerCell.setPadding(5f);
            headerCell.setColspan(10); // Set colspan to span 7 columns
         // Create a table for the header text and date
            PdfPTable headerTable = new PdfPTable(1);

            // Set the total width percentage of the table (optional)
            headerTable.setWidthPercentage(100);

            // Create the cell for the header text
            PdfPCell headerTextCell = new PdfPCell(new Phrase("Collection Day Sheet " + formattedDueDate + " and Branch " + branchName, headerFont));
            headerTextCell.setColspan(10); // Set the colspan to span all columns
            headerTextCell.setBorder(Rectangle.OUT_BOTTOM );
            headerTable.addCell(headerTextCell);

            // Add the header table to the header cell
            headerCell.addElement(headerTable);

            // Add the header cell to the first column of the table
            table.addCell(headerCell);




            // Set remaining header cells
            headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            headerCell.setNoWrap(true);
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

            // Add table rows
            int serialNumber = 1;
            int totalCollection = 0; 
            for (Map<String, Object> data : responseData) {
                LoanRepayment loanRepayment = (LoanRepayment) data.get("loanRepayment");
                Lead lead = (Lead) data.get("lead");
                List<Customer> borrowers = (List<Customer>) data.get("borrowers");
                List<Customer> coBorrowers = (List<Customer>) data.get("coBorrowers");

                String borrowerName = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getCname() : "";
                String coBorrowerName = (coBorrowers != null && !coBorrowers.isEmpty()) ? coBorrowers.get(0).getCname() : "";
                String address = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getAddl1() : "";
                String address1 = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getAddl2() : "";  
                String mobileNo = (borrowers != null && !borrowers.isEmpty()) ? borrowers.get(0).getMobnumber() : "";
                Integer loanID=loanRepayment.getLoanId();
                // Calculate total collection
                 totalCollection += loanRepayment.getEmi();

                // Add table row
                 PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(serialNumber), cellFont));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(String.valueOf(loanRepayment.getLoanId()), cellFont));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(borrowerName, cellFont));
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(coBorrowerName, cellFont));
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(mobileNo, cellFont));
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(address + address1, cellFont));
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(String.valueOf(loanRepayment.getEmi()), cellFont));
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);
;
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
//            totalCell.setBorder(Rectangle.NO_BORDER);
            table.addCell(totalCell);

         // Create a new row for the total collection
            PdfPRow totalCollectionRow = new PdfPRow(new PdfPCell[] {
                new PdfPCell(new Phrase("Total Collection:", headerFont)), // Cell with "Total Collection:" label
                new PdfPCell(),  
                new PdfPCell(),  
                new PdfPCell(),  new PdfPCell(),  new PdfPCell(),  
                new PdfPCell(new Phrase(String.valueOf(totalCollection), cellFont)), // Cell with the total collection value
                new PdfPCell(),   new PdfPCell(),      new PdfPCell(),  });
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
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(baos.size())
                    .body(baos.toByteArray());

        } catch (DocumentException e) {
            e.printStackTrace();
             
        }

        // Return an error response if there was an exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
	
	
	
	 
	
						
	//Collection Update (CDS Update)
						
	@PostMapping("/cds-update/") 
	public  ResponseEntity<List<Map<String, Object>>> postData(@RequestParam("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                                @RequestParam("branchId") Integer branchId,
                                @RequestParam("emi") Integer emi,
                                @RequestParam("loanId") Integer loanId) {

									
				// Fetch the loan repayment data based on due date and branch ID
				List<LoanRepayment> loanRepayments = loanRepaymentRepository.findByDueDateAndBranchId(dueDate, branchId);
						 
							
				// Retrieve the authentication object from the security context
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				// Get the user details from the authentication object
				User userDetails = (User) authentication.getPrincipal();
				// Get the current user's ID as a Long
			    Integer userId =  (int) userDetails.getId();
			    // Get the current user's name
			    String username = userDetails.getUsername();
						    
			    // Create a list to hold the response data
				List<Map<String, Object>> responseData = new ArrayList<>();
							
				for (LoanRepayment loanRepayment : loanRepayments) {
					// Retrieve loan details
					List<LoanCreation> loan = loanCreationRepository.getLoanCreationByleadid(loanRepayment.getLoanId());
							
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
//						loanRepaymentData.put("loanRepayment", loanRepayment);
//						loanRepaymentData.put("lead", lead);
//						loanRepaymentData.put("borrowers", borrowers);
//						loanRepaymentData.put("coBorrowers", coBorrowers);
//						loanRepaymentData.put("centers", centers);
//						loanRepaymentData.put("loan", loan);
							
						// Retrieve branch details
						Optional<Branch> branch = branchRepository.findById(Long.valueOf(branchId));
						loanRepaymentData.put("branch", branch.orElse(null));
							
						// Retrieve time details
						Integer timeId = centers.get(0).getTime();
						List<Time> times = timeRepository.getTimeBytid(timeId);
//						loanRepaymentData.put("times", times);
							
						// Retrieve day details
						Integer dayId = centers.get(0).getCmday();
						List<Days> days = daysRepository.getDayBydid(dayId);
//						loanRepaymentData.put("days", days);
							
							
//						Integer ammount=(int)loanRepayments.get(0).getEmi();
//						Integer collBranch=(int)loanRepayments.get(0).getBranchId(); 

						// Add the data for this loan repayment to the response list
						responseData.add(loanRepaymentData);							}
					}
//						Integer collLoanId=(int)loanRepayments.get(0).getLoanId();
							
							
					Collection collection=new Collection();
					collection.setCollAmmount(emi);
					collection.setCollBranchId(branchId );
					collection.setCollBy(userId);
					collection.setLoanId(loanId);
					collection.setCollStatus(1);
					collection.setCollType(1);
					collection.setCollDate(dueDate);
					collectionRepository.save(collection);
							
					// Return the response data as ResponseEntity
				    return ResponseEntity.ok(responseData);
						}

	
	//get Disbursement register					
		@GetMapping("/list/")
		public ResponseEntity<List<Map<String, Object>>> findBytoDateTofromDate(@RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
								@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate) {
							List<LoanCreation> loan=loanCreationRepository.findByddateBetween(toDate, fromDate);
			                 
                    // Retrieve the authentication object from the security context
				    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				    // Get the user details from the authentication object
//				    User userDetails = (User) authentication.getPrincipal();
//				    // Get the current user's ID as a Long
//				    Integer userId =  (int) userDetails.getId();
//				    // Get the current user's name
//				    String username = userDetails.getUsername();
				    
				    
					// Create a list to hold the response data
					List<Map<String, Object>> responseData = new ArrayList<>();
					
					for (LoanCreation loanCreation : loan) { 
					Scheme scheme =     schemeRepository.findById(loanCreation.getScheme());   
					// Retrieve lead details
					List<Lead> leads = leadRepository.findByleadID(loanCreation.getLeadid());
					if (!leads.isEmpty()) {
					Lead lead = leads.get(0);
					
					// Retrieve borrower details
					List<Customer> borrowers = customerRepository.findBycid(lead.getBorrowerID());
					List<Customer> coBorrowers = customerRepository.findBycid(lead.getCoBorrowerId());
					
					// Retrieve center details
					List<Center> centers = centerRepository.getCenterByncid(lead.getCenterID());
					
					// Create a map to hold the data for this loan repayment
					Map<String, Object> loanRepaymentData = new HashMap<>();
					loanRepaymentData.put("scheme", scheme);
					loanRepaymentData.put("lead", lead);
					loanRepaymentData.put("borrowers", borrowers);
					loanRepaymentData.put("coBorrowers", coBorrowers);
					loanRepaymentData.put("centers", centers); 
					loanRepaymentData.put("loan", loanCreation);
					
					// Retrieve branch details
					Optional<Branch> branch = branchRepository.findById(lead.getBranchID());
					loanRepaymentData.put("branch", branch.orElse(null));
					
					// Retrieve time details
					Integer timeId = centers.get(0).getTime();
					List<Time> times = timeRepository.getTimeBytid(timeId);
					loanRepaymentData.put("times", times);
					
					// Retrieve day details
					Integer dayId = centers.get(0).getCmday();
					List<Days> days = daysRepository.getDayBydid(dayId);
		
					loanRepaymentData.put("days", days);
					
					Optional<User> userDetails =   userRepository.findById((long) loanCreation.getSourcedby());
					loanRepaymentData.put("user", userDetails);
//				 Integer ammount=(int)loanRepayments.get(0).getEmi();
//					Integer collBranch=(int)loanRepayments.get(0).getBranchId(); 

					// Add the data for this loan repayment to the response list
					responseData.add(loanRepaymentData);
					}

				}
					// Return the response data as ResponseEntity
				    return ResponseEntity.ok(responseData);
			
		    }

}
