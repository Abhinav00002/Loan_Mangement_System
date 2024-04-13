package com.lms.model.foreclose;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foreclose_master")
public class ForeClose {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "foreclose_id")
	private int id;
	
	private int loanId;
	private int branchId;
	private int centerId;
	private int borrowerId;
	private int collAmount;
	private int collBy;
	private Date collDate;
	private int status;
	private String remark;
	public ForeClose() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public int getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}
	public int getCollAmount() {
		return collAmount;
	}
	public void setCollAmount(int collAmount) {
		this.collAmount = collAmount;
	}
	public int getCollBy() {
		return collBy;
	}
	public void setCollBy(int collBy) {
		this.collBy = collBy;
	}
	public Date getCollDate() {
		return collDate;
	}
	public void setCollDate(Date collDate) {
		this.collDate = collDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public ForeClose(int id, int loanId, int branchId, int centerId, int borrowerId, int collAmount, int collBy,
			Date collDate, int status, String remark) {
		super();
		this.id = id;
		this.loanId = loanId;
		this.branchId = branchId;
		this.centerId = centerId;
		this.borrowerId = borrowerId;
		this.collAmount = collAmount;
		this.collBy = collBy;
		this.collDate = collDate;
		this.status = status;
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ForeClose [id=" + id + ", loanId=" + loanId + ", branchId=" + branchId + ", centerId=" + centerId
				+ ", borrowerId=" + borrowerId + ", collAmount=" + collAmount + ", collBy=" + collBy + ", collDate="
				+ collDate + ", status=" + status + ", remark=" + remark + "]";
	}
	
	
	
	
}
