package com.lms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;
@Entity
public class AccountStatement {

	 
	    @Id
	    private long id;
	    private Double depositAmount;
	    private Double withdrawalAmount;
	    private Date dcrDate;
	    private Date depositDate;
	    private String branchName;
	    private int accountId;
	    private String accountName;
	    private Integer accountBankId;
	    private String accountBankName;
	    private String accountBankCity;
	    private String accountBankType;
	    private int accountBankStatus;
	    private long salaryAmount;
	    private int salaryId;
	    private Date salaryDate;
	    private Date salaryEntryDate;
	    private String remark;
	    @Transient
	    private double balance;
	
	public AccountStatement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Double getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(Double withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getAccountBankId() {
		return accountBankId;
	}

	public void setAccountBankId(Integer accountBankId) {
		this.accountBankId = accountBankId;
	}

	public String getAccountBankName() {
		return accountBankName;
	}

	public void setAccountBankName(String accountBankName) {
		this.accountBankName = accountBankName;
	}

	public String getAccountBankCity() {
		return accountBankCity;
	}

	public void setAccountBankCity(String accountBankCity) {
		this.accountBankCity = accountBankCity;
	}

	public String getAccountBankType() {
		return accountBankType;
	}

	public void setAccountBankType(String accountBankType) {
		this.accountBankType = accountBankType;
	}

	public int getAccountBankStatus() {
		return accountBankStatus;
	}

	public void setAccountBankStatus(int accountBankStatus) {
		this.accountBankStatus = accountBankStatus;
	}

	public double getSalaryAmount() {
		return salaryAmount;
	}

	public void setSalaryAmount(long d) {
		this.salaryAmount = d;
	}

	public long getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(int l) {
		this.salaryId = l;
	}

	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}

	public Date getSalaryEntryDate() {
		return salaryEntryDate;
	}

	public void setSalaryEntryDate(Date salaryEntryDate) {
		this.salaryEntryDate = salaryEntryDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public AccountStatement(long id, Double depositAmount, Double withdrawalAmount, Date dcrDate, Date depositDate,
			String branchName, int accountId, String accountName, Integer accountBankId, String accountBankName,
			String accountBankCity, String accountBankType, int accountBankStatus, long salaryAmount, int salaryId,
			Date salaryDate, Date salaryEntryDate, String remark, double balance) {
		super();
		this.id = id;
		this.depositAmount = depositAmount;
		this.withdrawalAmount = withdrawalAmount;
		this.dcrDate = dcrDate;
		this.depositDate = depositDate;
		this.branchName = branchName;
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountBankId = accountBankId;
		this.accountBankName = accountBankName;
		this.accountBankCity = accountBankCity;
		this.accountBankType = accountBankType;
		this.accountBankStatus = accountBankStatus;
		this.salaryAmount = salaryAmount;
		this.salaryId = salaryId;
		this.salaryDate = salaryDate;
		this.salaryEntryDate = salaryEntryDate;
		this.remark = remark;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountStatement [id=" + id + ", depositAmount=" + depositAmount + ", withdrawalAmount="
				+ withdrawalAmount + ", dcrDate=" + dcrDate + ", depositDate=" + depositDate + ", branchName="
				+ branchName + ", accountId=" + accountId + ", accountName=" + accountName + ", accountBankId="
				+ accountBankId + ", accountBankName=" + accountBankName + ", accountBankCity=" + accountBankCity
				+ ", accountBankType=" + accountBankType + ", accountBankStatus=" + accountBankStatus
				+ ", salaryAmount=" + salaryAmount + ", salaryId=" + salaryId + ", salaryDate=" + salaryDate
				+ ", salaryEntryDate=" + salaryEntryDate + ", remark=" + remark + ", balance=" + balance + "]";
	}

	 
	 
	
	
 	
}
