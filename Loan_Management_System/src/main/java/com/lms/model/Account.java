package com.lms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "account_master")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String accountName;
	private String bankName;
	private int bankId;
	private String bankCity;
	private String bankType;
	@Column(name = "entry_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private LocalDate entryDate;
	private int status;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Account(int id, String accountName, String bankName, int bankId, String bankCity, String bankType,
			LocalDate entryDate, int status) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.bankName = bankName;
		this.bankId = bankId;
		this.bankCity = bankCity;
		this.bankType = bankType;
		this.entryDate = entryDate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountName=" + accountName + ", bankName=" + bankName + ", bankId=" + bankId
				+ ", bankCity=" + bankCity + ", bankType=" + bankType + ", entryDate=" + entryDate + ", status="
				+ status + "]";
	}

}
