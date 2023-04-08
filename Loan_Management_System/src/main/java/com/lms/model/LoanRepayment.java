package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loan_repayment_master")
public class LoanRepayment {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	@Column(name="loan_id")
	private Long loanId;
	private Date dueDate;
	private long installmentNo;
	private double emi;
	private double openningAmount;
	private double principle;
	private double intrest;
	private long status;
	private Date collectionDate;
	private long collectionBy;
	
	
	
	
	
	
	
	public LoanRepayment() {
		super();
		// TODO Auto-generated constructor stub
	}







	public long getId() {
		return id;
	}







	public void setId(long id) {
		this.id = id;
	}







	public Date getDueDate() {
		return dueDate;
	}







	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}







	public long getInstallmentNo() {
		return installmentNo;
	}







	public void setInstallmentNo(long installmentNo) {
		this.installmentNo = installmentNo;
	}







	public double getEmi() {
		return emi;
	}







	public void setEmi(double emi2) {
		this.emi = emi2;
	}







	public double getOpenningAmount() {
		return openningAmount;
	}







	public void setOpenningAmount(double openningAmount) {
		this.openningAmount = openningAmount;
	}







	public double getPrinciple() {
		return principle;
	}







	public void setPrinciple(double principle) {
		this.principle = principle;
	}







	public double getIntrest() {
		return intrest;
	}







	public void setIntrest(double intrest) {
		this.intrest = intrest;
	}







	public long getStatus() {
		return status;
	}







	public void setStatus(long status) {
		this.status = status;
	}







	public Date getCollectionDate() {
		return collectionDate;
	}







	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}







	public long getCollectionBy() {
		return collectionBy;
	}







	public void setCollectionBy(long schemeBy) {
		this.collectionBy = schemeBy;
	}







	public Long getLoanId() {
		return loanId;
	}







	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}







	public LoanRepayment(long id, Long loanId, Date dueDate, long installmentNo, double emi, double openningAmount,
			double principle, double intrest, long status, Date collectionDate, long collectionBy) {
		super();
		this.id = id;
		this.loanId = loanId;
		this.dueDate = dueDate;
		this.installmentNo = installmentNo;
		this.emi = emi;
		this.openningAmount = openningAmount;
		this.principle = principle;
		this.intrest = intrest;
		this.status = status;
		this.collectionDate = collectionDate;
		this.collectionBy = collectionBy;
	}







	@Override
	public String toString() {
		return "LoanRepayment [id=" + id + ", loanId=" + loanId + ", dueDate=" + dueDate + ", installmentNo="
				+ installmentNo + ", emi=" + emi + ", openningAmount=" + openningAmount + ", principle=" + principle
				+ ", intrest=" + intrest + ", status=" + status + ", collectionDate=" + collectionDate
				+ ", collectionBy=" + collectionBy + "]";
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
