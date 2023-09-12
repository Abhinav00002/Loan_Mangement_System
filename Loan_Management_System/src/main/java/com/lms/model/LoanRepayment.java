package com.lms.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="loan_repayment_master")
public class LoanRepayment {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="loan_id")
	private Integer loanId;
	private LocalDate dueDate;
	private long installmentNo;
	private double emi;
	private double openningAmount;
	private double principle;
	private double intrest;
	private long status;
	 
	private LocalDate collectionDate;
	private long collectionBy;
	private int interestbook;
	private int preclose;
	@Column(name="branch_id")
	private int branchId;
	@Column(name = "center_id")
	private int centerId;
	 @Column(name = "coll_amount", columnDefinition = "DOUBLE DEFAULT 0.0")
	    private double collAmount;
	
	
	
	
	
	
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







	public Integer getLoanId() {
		return loanId;
	}







	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}







	public LocalDate getDueDate() {
		return dueDate;
	}







	public void setDueDate(LocalDate dueDate) {
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







	public void setEmi(double emi) {
		this.emi = emi;
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







	public LocalDate getCollectionDate() {
		return collectionDate;
	}







	public void setCollectionDate(LocalDate collectionDate) {
		this.collectionDate = collectionDate;
	}







	public long getCollectionBy() {
		return collectionBy;
	}







	public void setCollectionBy(long collectionBy) {
		this.collectionBy = collectionBy;
	}







	public int getInterestbook() {
		return interestbook;
	}







	public void setInterestbook(int interestbook) {
		this.interestbook = interestbook;
	}







	public int getPreclose() {
		return preclose;
	}







	public void setPreclose(int preclose) {
		this.preclose = preclose;
	}







	public int getBranchId() {
		return branchId;
	}







	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}







	public int getCenterId() {
		return centerId;
	}







	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}







	public double getCollAmount() {
		return collAmount;
	}







	public void setCollAmount(double collAmount) {
		this.collAmount = collAmount;
	}







	public LoanRepayment(long id, Integer loanId, LocalDate dueDate, long installmentNo, double emi,
			double openningAmount, double principle, double intrest, long status, LocalDate collectionDate,
			long collectionBy, int interestbook, int preclose, int branchId, int centerId, double collAmount) {
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
		this.interestbook = interestbook;
		this.preclose = preclose;
		this.branchId = branchId;
		this.centerId = centerId;
		this.collAmount = collAmount;
	}







	@Override
	public String toString() {
		return "LoanRepayment [id=" + id + ", loanId=" + loanId + ", dueDate=" + dueDate + ", installmentNo="
				+ installmentNo + ", emi=" + emi + ", openningAmount=" + openningAmount + ", principle=" + principle
				+ ", intrest=" + intrest + ", status=" + status + ", collectionDate=" + collectionDate
				+ ", collectionBy=" + collectionBy + ", interestbook=" + interestbook + ", preclose=" + preclose
				+ ", branchId=" + branchId + ", centerId=" + centerId + ", collAmount=" + collAmount + "]";
	}






 




 	
}
