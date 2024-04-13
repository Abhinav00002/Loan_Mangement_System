package com.lms.controller.customer;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired; 
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
import org.springframework.web.multipart.MultipartFile;
 
import com.lms.model.Documents;
import com.lms.model.User;
import com.lms.model.address.DocumentType;
import com.lms.model.error.ErrorResponse;
import com.lms.repo.DocumentTypeRepository;
import com.lms.repo.DocumentsRepository;

@RestController
@CrossOrigin("*")
public class DocumentController {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	private DocumentsRepository documentsRepository; 
	
	
	//Get Document type
	@GetMapping("/document_type/list/")
	public List<DocumentType> getDocumentTypes() {
		List<DocumentType> documentTypes=new ArrayList<DocumentType>();
		documentTypes=documentTypeRepository.findAll();
		return documentTypes;
		}
	
	//save Document
	@PostMapping("/document/save/")
	public ResponseEntity<?> createDocuments(@RequestParam("document") MultipartFile document,
	                                 @RequestParam("loanId") Integer loanId,
	                                 @RequestParam("docTypeId") Integer docTypeId) throws Exception {
		 
	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User userDetails = (User) authentication.getPrincipal();
	    Integer userId = (int) userDetails.getId();
//	    System.out.println("Document: " + document.getOriginalFilename() + ", LoanId: " + loanId + ", DocTypeId: " + docTypeId);

	    Documents newDocument = new Documents();
	    newDocument.setLoanId(loanId);
	    newDocument.setDocTypeId(docTypeId);
	    newDocument.setEntryBy(userId);
	    newDocument.setFileName(document.getOriginalFilename());

	    // Save the file to the target location
	    Path targetLocation = Paths.get("/home/docFile/clientDocs/" + document.getOriginalFilename());
	    Files.createDirectories(targetLocation.getParent());  
	    document.transferTo(targetLocation);

	   
	    newDocument.setDocument(targetLocation.toString());
 
	    return ResponseEntity.ok(documentsRepository.save(newDocument));
 }


	
	
	@GetMapping("/document_details/{loanId}")
	public ResponseEntity<?> getDocumentById(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId3(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();

	        // Read the file content into a byte array
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);

	        // Determine the appropriate Content-Type based on the file's extension
	        MediaType mediaType;
	        String fileName = document.getFileName();
	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else {
	            // If the file type is unknown, set it as application/octet-stream
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

	        // Prepare the HTTP headers
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType);
	        // Set the file name in the Content-Disposition header for download
	        headers.setContentDispositionFormData("attachment", fileName);

	        // Return the file content as a ResponseEntity with appropriate headers
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else {
	        // If the document with the given ID doesn't exist, return a 404 Not Found response
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("/document_details1/{loanId}")
	public ResponseEntity<?> getDocumentById1(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId1(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();
 
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);
 
	        MediaType mediaType;
	        String fileName = document.getFileName(); 

	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if (fileName.toLowerCase().endsWith(".gif")) {
	            mediaType = MediaType.IMAGE_GIF;
	        } else if (fileName.toLowerCase().endsWith(".txt")) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if (fileName.toLowerCase().endsWith(".mp4")) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else if (fileName.toLowerCase().endsWith(".avi")) {
	            mediaType = MediaType.valueOf("video/x-msvideo");
	        } else if (fileName.toLowerCase().endsWith(".mov")) {
	            mediaType = MediaType.valueOf("video/quicktime");
	        } else if (fileName.toLowerCase().endsWith(".mp3")) {
	            mediaType = MediaType.valueOf("audio/mpeg");
	        } else if (fileName.toLowerCase().endsWith(".wav")) {
	            mediaType = MediaType.valueOf("audio/wav");
	        } else if (fileName.toLowerCase().endsWith(".ogg")) {
	            mediaType = MediaType.valueOf("audio/ogg");
	        } else {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType); 
	        headers.setContentDispositionFormData("attachment", fileName);
 
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else { 
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/document_details2/{loanId}")
	public ResponseEntity<?> getDocumentById2(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId2(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();
 
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);
 
	        MediaType mediaType;
	        String fileName = document.getFileName(); 

	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if (fileName.toLowerCase().endsWith(".gif")) {
	            mediaType = MediaType.IMAGE_GIF;
	        } else if (fileName.toLowerCase().endsWith(".txt")) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if (fileName.toLowerCase().endsWith(".mp4")) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else if (fileName.toLowerCase().endsWith(".avi")) {
	            mediaType = MediaType.valueOf("video/x-msvideo");
	        } else if (fileName.toLowerCase().endsWith(".mov")) {
	            mediaType = MediaType.valueOf("video/quicktime");
	        } else if (fileName.toLowerCase().endsWith(".mp3")) {
	            mediaType = MediaType.valueOf("audio/mpeg");
	        } else if (fileName.toLowerCase().endsWith(".wav")) {
	            mediaType = MediaType.valueOf("audio/wav");
	        } else if (fileName.toLowerCase().endsWith(".ogg")) {
	            mediaType = MediaType.valueOf("audio/ogg");
	        } else {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType); 
	        headers.setContentDispositionFormData("attachment", fileName);
 
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else { 
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@GetMapping("/document_details3/{loanId}")
	public ResponseEntity<?> getDocumentById3(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId3(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();
 
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);
 
	        MediaType mediaType;
	        String fileName = document.getFileName(); 

	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if (fileName.toLowerCase().endsWith(".gif")) {
	            mediaType = MediaType.IMAGE_GIF;
	        } else if (fileName.toLowerCase().endsWith(".txt")) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if (fileName.toLowerCase().endsWith(".mp4")) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else if (fileName.toLowerCase().endsWith(".avi")) {
	            mediaType = MediaType.valueOf("video/x-msvideo");
	        } else if (fileName.toLowerCase().endsWith(".mov")) {
	            mediaType = MediaType.valueOf("video/quicktime");
	        } else if (fileName.toLowerCase().endsWith(".mp3")) {
	            mediaType = MediaType.valueOf("audio/mpeg");
	        } else if (fileName.toLowerCase().endsWith(".wav")) {
	            mediaType = MediaType.valueOf("audio/wav");
	        } else if (fileName.toLowerCase().endsWith(".ogg")) {
	            mediaType = MediaType.valueOf("audio/ogg");
	        } else {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType); 
	        headers.setContentDispositionFormData("attachment", fileName);
 
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else { 
	        return ResponseEntity.notFound().build();
	    }
	}

	@GetMapping("/document_details4/{loanId}")
	public ResponseEntity<?> getDocumentById4(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId4(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();
 
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);
 
	        MediaType mediaType;
	        String fileName = document.getFileName(); 

	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if (fileName.toLowerCase().endsWith(".gif")) {
	            mediaType = MediaType.IMAGE_GIF;
	        } else if (fileName.toLowerCase().endsWith(".txt")) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if (fileName.toLowerCase().endsWith(".mp4")) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else if (fileName.toLowerCase().endsWith(".avi")) {
	            mediaType = MediaType.valueOf("video/x-msvideo");
	        } else if (fileName.toLowerCase().endsWith(".mov")) {
	            mediaType = MediaType.valueOf("video/quicktime");
	        } else if (fileName.toLowerCase().endsWith(".mp3")) {
	            mediaType = MediaType.valueOf("audio/mpeg");
	        } else if (fileName.toLowerCase().endsWith(".wav")) {
	            mediaType = MediaType.valueOf("audio/wav");
	        } else if (fileName.toLowerCase().endsWith(".ogg")) {
	            mediaType = MediaType.valueOf("audio/ogg");
	        } else {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType); 
	        headers.setContentDispositionFormData("attachment", fileName);
 
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else { 
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@GetMapping("/document_details5/{loanId}")
	public ResponseEntity<?> getDocumentById5(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId5(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();
 
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);
 
	        MediaType mediaType;
	        String fileName = document.getFileName(); 

	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if (fileName.toLowerCase().endsWith(".gif")) {
	            mediaType = MediaType.IMAGE_GIF;
	        } else if (fileName.toLowerCase().endsWith(".txt")) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if (fileName.toLowerCase().endsWith(".mp4")) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else if (fileName.toLowerCase().endsWith(".avi")) {
	            mediaType = MediaType.valueOf("video/x-msvideo");
	        } else if (fileName.toLowerCase().endsWith(".mov")) {
	            mediaType = MediaType.valueOf("video/quicktime");
	        } else if (fileName.toLowerCase().endsWith(".mp3")) {
	            mediaType = MediaType.valueOf("audio/mpeg");
	        } else if (fileName.toLowerCase().endsWith(".wav")) {
	            mediaType = MediaType.valueOf("audio/wav");
	        } else if (fileName.toLowerCase().endsWith(".ogg")) {
	            mediaType = MediaType.valueOf("audio/ogg");
	        } else {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType); 
	        headers.setContentDispositionFormData("attachment", fileName);
 
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else { 
	        return ResponseEntity.notFound().build();
	    }
	}


	@GetMapping("/document_details6/{loanId}")
	public ResponseEntity<?> getDocumentById6(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId6(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();
 
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);
 
	        MediaType mediaType;
	        String fileName = document.getFileName(); 

	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if (fileName.toLowerCase().endsWith(".gif")) {
	            mediaType = MediaType.IMAGE_GIF;
	        } else if (fileName.toLowerCase().endsWith(".txt")) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if (fileName.toLowerCase().endsWith(".mp4")) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else if (fileName.toLowerCase().endsWith(".avi")) {
	            mediaType = MediaType.valueOf("video/x-msvideo");
	        } else if (fileName.toLowerCase().endsWith(".mov")) {
	            mediaType = MediaType.valueOf("video/quicktime");
	        } else if (fileName.toLowerCase().endsWith(".mp3")) {
	            mediaType = MediaType.valueOf("audio/mpeg");
	        } else if (fileName.toLowerCase().endsWith(".wav")) {
	            mediaType = MediaType.valueOf("audio/wav");
	        } else if (fileName.toLowerCase().endsWith(".ogg")) {
	            mediaType = MediaType.valueOf("audio/ogg");
	        } else {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType); 
	        headers.setContentDispositionFormData("attachment", fileName);
 
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else { 
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@GetMapping("/document_details7/{loanId}")
	public ResponseEntity<?> getDocumentById7(@PathVariable int loanId) throws IOException {
	    Optional<Documents> optionalDocument = documentsRepository.getByloanId7(loanId);

	    if (optionalDocument.isPresent()) {
	        Documents document = optionalDocument.get();
	        String filePath = document.getDocument();
 
	        Path fileLocation = Paths.get(filePath);
	        byte[] fileContent = Files.readAllBytes(fileLocation);
 
	        MediaType mediaType;
	        String fileName = document.getFileName(); 

	        if (fileName.toLowerCase().endsWith(".pdf")) {
	            mediaType = MediaType.APPLICATION_PDF;
	        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
	            mediaType = MediaType.IMAGE_JPEG;
	        } else if (fileName.toLowerCase().endsWith(".png")) {
	            mediaType = MediaType.IMAGE_PNG;
	        } else if (fileName.toLowerCase().endsWith(".gif")) {
	            mediaType = MediaType.IMAGE_GIF;
	        } else if (fileName.toLowerCase().endsWith(".txt")) {
	            mediaType = MediaType.TEXT_PLAIN;
	        } else if (fileName.toLowerCase().endsWith(".mp4")) {
	            mediaType = MediaType.valueOf("video/mp4");
	        } else if (fileName.toLowerCase().endsWith(".avi")) {
	            mediaType = MediaType.valueOf("video/x-msvideo");
	        } else if (fileName.toLowerCase().endsWith(".mov")) {
	            mediaType = MediaType.valueOf("video/quicktime");
	        } else if (fileName.toLowerCase().endsWith(".mp3")) {
	            mediaType = MediaType.valueOf("audio/mpeg");
	        } else if (fileName.toLowerCase().endsWith(".wav")) {
	            mediaType = MediaType.valueOf("audio/wav");
	        } else if (fileName.toLowerCase().endsWith(".ogg")) {
	            mediaType = MediaType.valueOf("audio/ogg");
	        } else {
	            mediaType = MediaType.APPLICATION_OCTET_STREAM;
	        }

 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(mediaType); 
	        headers.setContentDispositionFormData("attachment", fileName);
 
	        return ResponseEntity.ok().headers(headers).body(fileContent);
	    } else { 
	        return ResponseEntity.notFound().build();
	    }
	}

	
	@GetMapping("/getallLoanId/forloanAgreement/")
	public List<Map<String, Object>> getAll() {
		return documentsRepository.getAllLoanIdForLoanAgreement();
	}
	
}