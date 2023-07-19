package com.lms.model.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document_type_master")
public class DocumentType {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String documentType;
	
	
	public DocumentType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	 
	public String getDocumentType() {
		return documentType;
	}


	public DocumentType(int id, String documentType) {
		super();
		this.id = id;
		this.documentType = documentType;
	}


	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}


	@Override
	public String toString() {
		return "DocumentType [id=" + id + ", documentType=" + documentType + "]";
	}
	
}
