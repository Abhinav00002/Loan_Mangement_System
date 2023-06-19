package com.lms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lead_master")
public class Lead {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leadID;
	private int borrowerID;
	private int coBorrowerId; 
	private Long branchID;
	private Long centerID;
	private String sourcedBy;
	private String manageBy;
	private String aplicationDate;
	private String entryBy;
	
	
	
	public Lead() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getLeadID() {
		return leadID;
	}



	public void setLeadID(int leadID) {
		this.leadID = leadID;
	}



	public int getBorrowerID() {
		return borrowerID;
	}



	public void setBorrowerID(int borrowerID) {
		this.borrowerID = borrowerID;
	}



	public int getCoBorrowerId() {
		return coBorrowerId;
	}



	public void setCoBorrowerId(int coBorrowerId) {
		this.coBorrowerId = coBorrowerId;
	}



	public Long getBranchID() {
		return branchID;
	}



	public void setBranchID(Long string) {
		this.branchID = string;
	}



	public Long getCenterID() {
		return centerID;
	}



	public void setCenterID(Long string) {
		this.centerID = string;
	}



	public String getSourcedBy(String string) {
		return sourcedBy;
	}



	public void setSourcedBy(String sourcedBy) {
		this.sourcedBy = sourcedBy;
	}



	public String getManageBy() {
		return manageBy;
	}



	public void setManageBy(String manageBy) {
		this.manageBy = manageBy;
	}



	public String getAplicationDate() {
		return aplicationDate;
	}



	public void setAplicationDate(String aplicationDate) {
		this.aplicationDate = aplicationDate;
	}



	public String getEntryBy() {
		return entryBy;
	}



	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}



	public Lead(int leadID, int borrowerID, int coBorrowerId, Long branchID, Long centerID, String sourcedBy,
			String manageBy, String aplicationDate, String entryBy) {
		super();
		this.leadID = leadID;
		this.borrowerID = borrowerID;
		this.coBorrowerId = coBorrowerId;
		this.branchID = branchID;
		this.centerID = centerID;
		this.sourcedBy = sourcedBy;
		this.manageBy = manageBy;
		this.aplicationDate = aplicationDate;
		this.entryBy = entryBy;
	}



	@Override
	public String toString() {
		return "Lead [leadID=" + leadID + ", borrowerID=" + borrowerID + ", coBorrowerId=" + coBorrowerId
				+ ", branchID=" + branchID + ", centerID=" + centerID + ", sourcedBy=" + sourcedBy + ", manageBy="
				+ manageBy + ", aplicationDate=" + aplicationDate + ", entryBy=" + entryBy + "]";
	}
	
	
	
}
