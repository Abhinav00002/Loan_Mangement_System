package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = " portfolio_day")
public class PortfolioDay {
	
	@Id 
	private int loanId;
	private Date  dueDate;
	private Double openningAmount;
	private Date disbursementDate;
	private Double emipending;
	private int pendinginst;
	private int branchid;
	private int leadid;
	private int centerid;
	private int borrowerid;
	private int coBorrowerId;
	private int manageBy;
	@Column(name = "entrydate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date entrydate;
	private int dpd;
	
	
	
	
	public int getDpd() {
		return dpd;
	}




	public void setDpd(int dpd) {
		this.dpd = dpd;
	}




	public PortfolioDay() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getLoanId() {
		return loanId;
	}




	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}




	public Date getDueDate() {
		return dueDate;
	}




	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}




	public Double getOpenningAmount() {
		return openningAmount;
	}




	public void setOpenningAmount(Double openningAmount) {
		this.openningAmount = openningAmount;
	}




	public Date getDisbursementDate() {
		return disbursementDate;
	}




	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}




	public Double getEmipending() {
		return emipending;
	}




	public void setEmipending(Double emipending) {
		this.emipending = emipending;
	}




	public int getPendinginst() {
		return pendinginst;
	}




	public void setPendinginst(int pendinginst) {
		this.pendinginst = pendinginst;
	}




	public int getBranchid() {
		return branchid;
	}




	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}




	public int getLeadid() {
		return leadid;
	}




	public void setLeadid(int leadid) {
		this.leadid = leadid;
	}




	public int getCenterid() {
		return centerid;
	}




	public void setCenterid(int centerid) {
		this.centerid = centerid;
	}




	public int getBorrowerid() {
		return borrowerid;
	}




	public void setBorrowerid(int borrowerid) {
		this.borrowerid = borrowerid;
	}




	public int getCoBorrowerId() {
		return coBorrowerId;
	}




	public void setCoBorrowerId(int coBorrowerId) {
		this.coBorrowerId = coBorrowerId;
	}




	public int getManageBy() {
		return manageBy;
	}




	public void setManageBy(int manageBy) {
		this.manageBy = manageBy;
	}




	public Date getEntrydate() {
		return entrydate;
	}




	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}




	public PortfolioDay(int loanId, Date dueDate, Double openningAmount, Date disbursementDate, Double emipending,
			int pendinginst, int branchid, int leadid, int centerid, int borrowerid, int coBorrowerId, int manageBy,
			Date entrydate, int dpd) {
		super();
		this.loanId = loanId;
		this.dueDate = dueDate;
		this.openningAmount = openningAmount;
		this.disbursementDate = disbursementDate;
		this.emipending = emipending;
		this.pendinginst = pendinginst;
		this.branchid = branchid;
		this.leadid = leadid;
		this.centerid = centerid;
		this.borrowerid = borrowerid;
		this.coBorrowerId = coBorrowerId;
		this.manageBy = manageBy;
		this.entrydate = entrydate;
		this.dpd = dpd;
	}




	@Override
	public String toString() {
		return "PortfolioDay [loanId=" + loanId + ", dueDate=" + dueDate + ", openningAmount=" + openningAmount
				+ ", disbursementDate=" + disbursementDate + ", emipending=" + emipending + ", pendinginst="
				+ pendinginst + ", branchid=" + branchid + ", leadid=" + leadid + ", centerid=" + centerid
				+ ", borrowerid=" + borrowerid + ", coBorrowerId=" + coBorrowerId + ", manageBy=" + manageBy
				+ ", entrydate=" + entrydate + ", dpd=" + dpd + "]";
	}




	 	
	
	
	

}
