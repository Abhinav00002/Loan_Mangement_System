
package com.lms.model;


import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "staff_document_master")
public class StaffDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int staffId;

    private int docTypeId;

 
    private String document;  
    private int entryBy;

    @Column(name = "entry_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDate entryDate;

    private int entryStatus;
    private String fileName;  


    
    

   

    public StaffDocument() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getStaffId() {
		return staffId;
	}



	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}



	public int getDocTypeId() {
		return docTypeId;
	}



	public void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}



	public String getDocument() {
		return document;
	}



	public void setDocument(String string) {
		this.document = string;
	}



	public int getEntryBy() {
		return entryBy;
	}



	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}



	public LocalDate getEntryDate() {
		return entryDate;
	}



	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}



	public int getEntryStatus() {
		return entryStatus;
	}



	public void setEntryStatus(int entryStatus) {
		this.entryStatus = entryStatus;
	}



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

	 


	public StaffDocument(int id, int staffId, int docTypeId, String document, int entryBy, LocalDate entryDate,
			int entryStatus, String fileName) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.docTypeId = docTypeId;
		this.document = document;
		this.entryBy = entryBy;
		this.entryDate = entryDate;
		this.entryStatus = entryStatus;
		this.fileName = fileName;
	}



	@Override
	public String toString() {
		return "StaffDocument [id=" + id + ", staffId=" + staffId + ", docTypeId=" + docTypeId + ", document=" + document
				+ ", entryBy=" + entryBy + ", entryDate=" + entryDate + ", entryStatus=" + entryStatus + ", fileName="
				+ fileName + "]";
	}



	 


 


	 
	
	
}

