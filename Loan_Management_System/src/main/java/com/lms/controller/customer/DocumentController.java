package com.lms.controller.customer;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lms.model.Branch;
import com.lms.model.Documents;
import com.lms.model.User;
import com.lms.model.address.DocumentType;
import com.lms.repo.DocumentTypeRepository;
import com.lms.repo.DocumentsRepository;

@RestController
@CrossOrigin("*")
public class DocumentController {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	private DocumentsRepository documentsRepository; 
	
	@GetMapping("/document_type/list/")
	public List<DocumentType> getDocumentTypes() {
		List<DocumentType> documentTypes=new ArrayList<DocumentType>();
		documentTypes=documentTypeRepository.findAll();
		return documentTypes;
		}
	
	
	@PostMapping("/document/save/")
	public Documents createDocuments(@RequestParam("document") MultipartFile document,
	                                 @RequestParam("loanId") int loanId,
	                                 @RequestParam("docTypeId") int docTypeId) throws Exception {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    User userDetails = (User) authentication.getPrincipal();
	    Integer userId = (int) userDetails.getId();
	    System.out.println("Document: " + document.getOriginalFilename() + ", LoanId: " + loanId + ", DocTypeId: " + docTypeId);

	    Documents newDocument = new Documents();
	    newDocument.setLoanId(loanId);
	    newDocument.setDocTypeId(docTypeId);
	    newDocument.setDocument(document.getBytes());
	    newDocument.setEntryBy(userId);

	    // Set the filename in the entity using the original filename from the multipart file
	    newDocument.setFileName(document.getOriginalFilename());

	    return documentsRepository.save(newDocument);
	}


	
	
	
	
	
	@GetMapping("/document/{id}")
	public ResponseEntity<byte[]> getDocument(@PathVariable("id") Integer id) throws IOException {
	    // Fetch the document from the database based on the given ID
	    Documents document = documentsRepository.findById(id).orElse(null);

	    if (document == null || document.getDocument() == null) {
	        return ResponseEntity.notFound().build();
	    }

	    byte[] documentData = document.getDocument();

	    // Determine the file type based on the file extension
	    String filename = document.getFileName(); // Use the actual filename from the entity
	    if (filename == null || filename.isEmpty()) {
	        filename = "filename.dat"; // Default filename if the filename is not available
	    } 
	    // Get the file extension from the filename
	    String fileExtension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

	    // Set the appropriate media type based on the file extension
	    MediaType mediaType;
	    switch (fileExtension) {
	        case "png":
	            mediaType = MediaType.IMAGE_PNG;
	            break;
	        case "jpg":
	        case "jpeg":
	            mediaType = MediaType.IMAGE_JPEG;
	            break;
	        case "pdf":
	            mediaType = MediaType.APPLICATION_PDF;
	            break;
	        case "mp4":
	            mediaType = MediaType.valueOf("video/mp4");
	            break;
	        case "mkv":
	            mediaType = MediaType.valueOf("video/x-matroska");
	            break;
	        default:
	            mediaType = MediaType.APPLICATION_OCTET_STREAM; // Use this as a fallback for unknown file types
	            break;
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(mediaType);
	    headers.setContentDispositionFormData("attachment", filename); 
	    return new ResponseEntity<>(documentData, headers, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/document/{id}/download")
    public ResponseEntity<Resource> downloadDocument(@PathVariable("id") Integer id) {
        Documents document = documentsRepository.findById(id).orElse(null);

        if (document == null || document.getDocument() == null) {
            // Document not found or document data is null
            return ResponseEntity.notFound().build();
        }

        // Prepare the response with the document data
        ByteArrayResource resource = new ByteArrayResource(document.getDocument());

        return ResponseEntity.ok()
                .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"document_" + id + ".pdf\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(document.getDocument().length)
                .body(resource);
    }
	
}
