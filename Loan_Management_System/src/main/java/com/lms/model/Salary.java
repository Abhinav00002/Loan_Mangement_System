package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salary_paid")
public class Salary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="salary_id")
	private int salaryId;
	private int account;
	private int staffId;
	private String branchName;
	private Date salaryDate;
	private int amount;
	private int entryBy; 
	private Date entryDate;
	private String remark;
	
	
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getSalaryId() {
		return salaryId;
	}


	public void setSalaryId(int salaryId) {
		this.salaryId = salaryId;
	}


	public int getAccount() {
		return account;
	}


	public void setAccount(int account) {
		this.account = account;
	}


	public int getStaffId() {
		return staffId;
	}


	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}


	public Date getSalaryDate() {
		return salaryDate;
	}


	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getEntryBy() {
		return entryBy;
	}


	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}


	public Date getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Salary(int salaryId, int account, int staffId, String branchName, Date salaryDate, int amount, int entryBy,
			Date entryDate, String remark) {
		super();
		this.salaryId = salaryId;
		this.account = account;
		this.staffId = staffId;
		this.branchName = branchName;
		this.salaryDate = salaryDate;
		this.amount = amount;
		this.entryBy = entryBy;
		this.entryDate = entryDate;
		this.remark=remark;
	}


	@Override
	public String toString() {
		return "Salary [salaryId=" + salaryId + ", account=" + account + ", staffId=" + staffId + ", branchName="
				+ branchName + ", salaryDate=" + salaryDate + ", amount=" + amount + ", entryBy=" + entryBy
				+ ", entryDate=" + entryDate + ", remark=" + remark + "]";
	}


	 


	 	
	

}
