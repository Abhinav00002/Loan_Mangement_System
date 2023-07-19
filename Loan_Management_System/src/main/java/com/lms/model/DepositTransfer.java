package com.lms.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "deposit_transfer")
public class DepositTransfer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fbranchName;
	private int fbranchId;
	private String tbranchName;
	private int tbranchId;
	private int bankId;
	private String bankName;

	@Column(name="entry_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private LocalDate entryDate;
	private int clearStatus;
	private Date fdcrDate;
	private int entryBy;
	
	
	
	public DepositTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFbranchName() {
		return fbranchName;
	}



	public void setFbranchName(String fbranchName) {
		this.fbranchName = fbranchName;
	}



	public int getFbranchId() {
		return fbranchId;
	}



	public void setFbranchId(int fbranchId) {
		this.fbranchId = fbranchId;
	}



	public String getTbranchName() {
		return tbranchName;
	}



	public void setTbranchName(String tbranchName) {
		this.tbranchName = tbranchName;
	}



	public int getTbranchId() {
		return tbranchId;
	}



	public void setTbranchId(int tbranchId) {
		this.tbranchId = tbranchId;
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



	public Date getFdcrDate() {
		return fdcrDate;
	}



	public void setFdcrDate(Date fdcrDate) {
		this.fdcrDate = fdcrDate;
	}



	public int getEntryBy() {
		return entryBy;
	}



	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}



	public DepositTransfer(int id, String fbranchName, int fbranchId, String tbranchName, int tbranchId, int bankId,
			String bankName, LocalDate entryDate, int clearStatus, Date fdcrDate, int entryBy) {
		super();
		this.id = id;
		this.fbranchName = fbranchName;
		this.fbranchId = fbranchId;
		this.tbranchName = tbranchName;
		this.tbranchId = tbranchId;
		this.bankId = bankId;
		this.bankName = bankName;
		this.entryDate = entryDate;
		this.clearStatus = clearStatus;
		this.fdcrDate = fdcrDate;
		this.entryBy = entryBy;
	}



	@Override
	public String toString() {
		return "DepositTransfer [id=" + id + ", fbranchName=" + fbranchName + ", fbranchId=" + fbranchId
				+ ", tbranchName=" + tbranchName + ", tbranchId=" + tbranchId + ", bankId=" + bankId + ", bankName="
				+ bankName + ", entryDate=" + entryDate + ", clearStatus=" + clearStatus + ", fdcrDate=" + fdcrDate
				+ ", entryBy=" + entryBy + "]";
	}

	
	
	
	
}
