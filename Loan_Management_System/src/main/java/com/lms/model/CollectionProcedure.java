package com.lms.model;

import javax.persistence.Entity;

import java.time.LocalDate;

import javax.persistence.*;


@Entity
public class CollectionProcedure {
	
	

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer Id;
	private int loanId;
	private int emi;
	private LocalDate dueDate;
	private int branchId;
	
	
	
	
	public CollectionProcedure() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Integer getId() {
		return Id;
	}




	public void setId(Integer id) {
		Id = id;
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




	public LocalDate getDueDate() {
		return dueDate;
	}




	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}




	public int getBranchId() {
		return branchId;
	}




	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}




	@Override
	public String toString() {
		return "CollectionProcedure [Id=" + Id + ", loanId=" + loanId + ", emi=" + emi + ", dueDate=" + dueDate
				+ ", branchId=" + branchId + "]";
	}




	public CollectionProcedure(Integer id, int loanId, int emi, LocalDate dueDate, int branchId) {
		super();
		Id = id;
		this.loanId = loanId;
		this.emi = emi;
		this.dueDate = dueDate;
		this.branchId = branchId;
	}
	 

	 
	
	
	

}
