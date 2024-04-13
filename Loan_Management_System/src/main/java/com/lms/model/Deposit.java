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
@Table(name = "deposit_master")
public class Deposit {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int branchId;
	private String branchName;
	private Double amount;
	private int bankId;
	private String bankName;
	private  String bankType;
	@Column(name="entry_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private LocalDate entryDate;
	private int clearStatus;
	private Date dcrDate;
	private Date depositDate;
	private int entryBy;
	private int clearBy;
	private Date clearDate;
	private int accountId;
	private String remark;
	
	
	
	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getBranchId() {
		return branchId;
	}




	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}




	public String getBranchName() {
		return branchName;
	}




	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}




	public Double getAmount() {
		return amount;
	}




	public void setAmount(Double amount) {
		this.amount = amount;
	}




	public int getBankId() {
		return bankId;
	}




	public void setBankId(int bankId) {
		this.bankId = bankId;
	}




	public String getBankName() {
		return bankName;
	}




	public void setBankName(String bankName) {
		this.bankName = bankName;
	}




	public String getBankType() {
		return bankType;
	}




	public void setBankType(String bankType) {
		this.bankType = bankType;
	}




	public LocalDate getEntryDate() {
		return entryDate;
	}




	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}




	public int getClearStatus() {
		return clearStatus;
	}




	public void setClearStatus(int clearStatus) {
		this.clearStatus = clearStatus;
	}




	public Date getDcrDate() {
		return dcrDate;
	}




	public void setDcrDate(Date dcrDate) {
		this.dcrDate = dcrDate;
	}




	public Date getDepositDate() {
		return depositDate;
	}




	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}




	public int getEntryBy() {
		return entryBy;
	}




	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}




	public int getClearBy() {
		return clearBy;
	}




	public void setClearBy(int clearBy) {
		this.clearBy = clearBy;
	}




	public Date getClearDate() {
		return clearDate;
	}




	public void setClearDate(Date clearDate) {
		this.clearDate = clearDate;
	}




	public int getAccountId() {
		return accountId;
	}




	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}




	public Deposit(int id, int branchId, String branchName, Double amount, int bankId, String bankName, String bankType,
			LocalDate entryDate, int clearStatus, Date dcrDate, Date depositDate, int entryBy, int clearBy,
			Date clearDate, int accountId, String remark) {
		super();
		this.id = id;
		this.branchId = branchId;
		this.branchName = branchName;
		this.amount = amount;
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankType = bankType;
		this.entryDate = entryDate;
		this.clearStatus = clearStatus;
		this.dcrDate = dcrDate;
		this.depositDate = depositDate;
		this.entryBy = entryBy;
		this.clearBy = clearBy;
		this.clearDate = clearDate;
		this.accountId = accountId;
		this.remark = remark;
	}




	@Override
	public String toString() {
		return "Deposit [id=" + id + ", branchId=" + branchId + ", branchName=" + branchName + ", amount=" + amount
				+ ", bankId=" + bankId + ", bankName=" + bankName + ", bankType=" + bankType + ", entryDate="
				+ entryDate + ", clearStatus=" + clearStatus + ", dcrDate=" + dcrDate + ", depositDate=" + depositDate
				+ ", entryBy=" + entryBy + ", clearBy=" + clearBy + ", clearDate=" + clearDate + ", accountId="
				+ accountId + ", remark=" + remark + "]";
	}




 


 	
}
