package com.lms.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.lms.model.StaffDocument;
import com.lms.model.StaffDocument;
import com.lms.model.User;
import com.lms.model.address.DocumentType;
import com.lms.repo.DocumentTypeRepository;
import com.lms.repo.StaffDocumentRepository;

@RestController
@CrossOrigin("*")
public class StaffDocumentController {
	
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	private StaffDocumentRepository staffDocumentRepository; 
	

	//save Staff Document
	@PostMapping("/staffDocument/save/")
	public StaffDocument createStaffDocument(@RequestParam("document") MultipartFile document,
	                                 @RequestParam("staffId") int staffId,
	                                 @RequestParam("docTypeId") int docTypeId) throws Exception {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User userDetails = (User) authentication.getPrincipal();
	    Integer userId = (int) userDetails.getId();
//	    System.out.println("Document: " + document.getOriginalFilename() + ", staffId: " + staffId + ", DocTypeId: " + docTypeId);

	    StaffDocument newDocument = new StaffDocument();
	    newDocument.setStaffId(staffId);
	    newDocument.setDocTypeId(docTypeId);
	    newDocument.setEntryBy(userId);
	    newDocument.setFileName(document.getOriginalFilename());

	    // Save the file to the target location
	    Path targetLocation = Paths.get("/home/docFile/staffDocs/" + document.getOriginalFilename());
	    Files.createDirectories(targetLocation.getParent());  
	    document.transferTo(targetLocation);

	   
	    newDocument.setDocument(targetLocation.toString());
 
	    return staffDocumentRepository.save(newDocument);
	}


 
		
	

	@GetMapping("/staff_document_details/{staffId}")
	public ResponseEntity<?> getDocumentById(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId3(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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

	@GetMapping("/staff_document_details1/{staffId}")
	public ResponseEntity<?> getDocumentById1(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId1(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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
	
	@GetMapping("/staff_document_details2/{staffId}")
	public ResponseEntity<?> getDocumentById2(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId2(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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

	
	@GetMapping("/staff_document_details3/{staffId}")
	public ResponseEntity<?> getDocumentById3(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId3(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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

	@GetMapping("/staff_document_details4/{staffId}")
	public ResponseEntity<?> getDocumentById4(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId4(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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

	
	@GetMapping("/staff_document_details5/{staffId}")
	public ResponseEntity<?> getDocumentById5(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId5(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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


	@GetMapping("/staff_document_details6/{staffId}")
	public ResponseEntity<?> getDocumentById6(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId6(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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

	
	@GetMapping("/staff_document_details7/{staffId}")
	public ResponseEntity<?> getDocumentById7(@PathVariable int staffId) throws IOException {
	    Optional<StaffDocument> optionalDocument = staffDocumentRepository.getBystaffId7(staffId);

	    if (optionalDocument.isPresent()) {
	        StaffDocument document = optionalDocument.get();
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



}
