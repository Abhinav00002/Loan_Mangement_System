package com.lms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_Remark")
public class ClientRemark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientRemarkId;
	private int loanId;
	private int borrowerId;
	private int branchId;
	private int centerId;
	private String contactNo;
	private int clientManageBy;
	private int remarkType;
	private String remark;
	private Date remarkDate;
	private int remarkBy;
	
	
	public ClientRemark() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getClientRemarkId() {
		return clientRemarkId;
	}


	public void setClientRemarkId(int clientRemarkId) {
		this.clientRemarkId = clientRemarkId;
	}


	public int getLoanId() {
		return loanId;
	}


	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}


	public int getBorrowerId() {
		return borrowerId;
	}


	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
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


	public String getContactNo() {
		return contactNo;
	}


	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	public int getClientManageBy() {
		return clientManageBy;
	}


	public void setClientManageBy(int clientManageBy) {
		this.clientManageBy = clientManageBy;
	}


	public int getRemarkType() {
		return remarkType;
	}


	public void setRemarkType(int remarkType) {
		this.remarkType = remarkType;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getRemarkDate() {
		return remarkDate;
	}


	public void setRemarkDate(Date remarkDate) {
		this.remarkDate = remarkDate;
	}


	public int getRemarkBy() {
		return remarkBy;
	}


	public void setRemarkBy(int remarkBy) {
		this.remarkBy = remarkBy;
	}


	public ClientRemark(int clientRemarkId, int loanId, int borrowerId, int branchId, int centerId, String contactNo,
			int clientManageBy, int remarkType, String remark, Date remarkDate, int remarkBy) {
		super();
		this.clientRemarkId = clientRemarkId;
		this.loanId = loanId;
		this.borrowerId = borrowerId;
		this.branchId = branchId;
		this.centerId = centerId;
		this.contactNo = contactNo;
		this.clientManageBy = clientManageBy;
		this.remarkType = remarkType;
		this.remark = remark;
		this.remarkDate = remarkDate;
		this.remarkBy = remarkBy;
	}


	@Override
	public String toString() {
		return "ClientRemark [clientRemarkId=" + clientRemarkId + ", loanId=" + loanId + ", borrowerId=" + borrowerId
				+ ", branchId=" + branchId + ", centerId=" + centerId + ", contactNo=" + contactNo + ", clientManageBy="
				+ clientManageBy + ", remarkType=" + remarkType + ", remark=" + remark + ", remarkDate=" + remarkDate
				+ ", remarkBy=" + remarkBy + "]";
	}
	
	
	
	
}
