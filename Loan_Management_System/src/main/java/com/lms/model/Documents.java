
package com.lms.model;


import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "document_master")
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int loanId;

    private int docTypeId;

    @Lob
    private byte[] document; // Storing the document as byte[] (BLOB)

    private int entryBy;

    @Column(name = "entry_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDate entryDate;

    private int entryStatus;
    private String fileName; // New field to store the original filename


    public Documents() {
        // Default constructor
    }
 
    

    // Constructor

    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getLoanId() {
		return loanId;
	}



	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}



	public int getDocTypeId() {
		return docTypeId;
	}



	public void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}



	public byte[] getDocument() {
		return document;
	}



	public void setDocument(byte[] document) {
		this.document = document;
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

	public Documents(int id, int loanId, int docTypeId, byte[] document, int entryBy, LocalDate entryDate, int entryStatus) {
        this.id = id;
        this.loanId = loanId;
        this.docTypeId = docTypeId;
        this.document = document;
        this.entryBy = entryBy;
        this.entryDate = entryDate;
        this.entryStatus = entryStatus;
    }



	@Override
	public String toString() {
		return "Documents [id=" + id + ", loanId=" + loanId + ", docTypeId=" + docTypeId + ", document="
				+ Arrays.toString(document) + ", entryBy=" + entryBy + ", entryDate=" + entryDate + ", entryStatus="
				+ entryStatus + ", fileName=" + fileName + "]";
	}



	 
	
	
}
