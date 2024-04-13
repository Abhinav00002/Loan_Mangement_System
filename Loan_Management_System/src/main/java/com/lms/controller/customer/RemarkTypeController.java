package com.lms.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.address.Purpose;
import com.lms.model.address.RemarkType;
import com.lms.repo.customer.RemarkTypeRepository;

@RestController
@CrossOrigin("*")
public class RemarkTypeController {

	@Autowired
	private RemarkTypeRepository remarkTypeRepository;
	
	@GetMapping("/remarkType/list/")
	public List<RemarkType> getRemarkType(){
		return remarkTypeRepository.findAll();
	}
	
	@PostMapping("/add/remarkType")
	public ResponseEntity<String > saveRemarkType(@RequestBody RemarkType remarkType) {
	    System.out.println(remarkType);
	    remarkTypeRepository.save(remarkType);
 
	    return ResponseEntity.ok("Remark Type saved successfully.");
	}

}
