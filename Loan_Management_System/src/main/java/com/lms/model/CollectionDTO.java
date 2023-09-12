package com.lms.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CollectionDTO {
@Id
	private int loanId;
	private int emi;
//	private LocalDate dueDate;
//	private int branchId;
	
	
	
	public CollectionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getLoanId() {
		return loanId;
	}



	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}



	public int getEmi() {
		return emi;
	}



	public void setEmi(int emi) {
		this.emi = emi;
	}



//	public LocalDate getDueDate() {
//		return dueDate;
//	}
//
//
//
//	public void setDuedate(LocalDate dueDate) {
//		this.dueDate = dueDate;
//	}
//
//
//
//	public int getBranchId() {
//		return branchId;
//	}
//
//
//
//	public void setBranchId(int branchId) {
//		this.branchId = branchId;
//	}



	public CollectionDTO(int loanId, int emi ) {
		super();
		this.loanId = loanId;
		this.emi = emi; 
	}



	@Override
	public String toString() {
		return "CollectionDTO [loanId=" + loanId + ", emi=" + emi + " ]";
	}
	
	
	
}
