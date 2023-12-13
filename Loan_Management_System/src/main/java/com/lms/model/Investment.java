package com.lms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "investment_master")
public class Investment {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String lenderId;
	private Float amount;
	private String investmentType;
	private Date investmentDate;
	private String interestRate;
	private Date interestStartDate;
	private int status;
	private Date closeDate;
	private int entryBy;
	private int closeBy;
	  @Transient
	private String lenderName;
	
	
	public Investment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLenderId() {
		return lenderId;
	}


	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}


	public String getInvestmentType() {
		return investmentType;
	}


	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}


	public Date getInvestmentDate() {
		return investmentDate;
	}


	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}


	public String getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}


	public Date getInterestStartDate() {
		return interestStartDate;
	}


	public void setInterestStartDate(Date interestStartDate) {
		this.interestStartDate = interestStartDate;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Date getCloseDate() {
		return closeDate;
	}


	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}


	public int getEntryBy() {
		return entryBy;
	}


	public void setEntryBy(int entryBy) {
		this.entryBy = entryBy;
	}


	public int getCloseBy() {
		return closeBy;
	}


	public void setCloseBy(int closeBy) {
		this.closeBy = closeBy;
	}

	

	public String getLenderName() {
		return lenderName;
	}


	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}


	public Investment(int id, String lenderId, Float amount, String investmentType, Date investmentDate,
			String interestRate, Date interestStartDate, int status, Date closeDate, int entryBy, int closeBy,
			String lenderName) {
		super();
		this.id = id;
		this.lenderId = lenderId;
		this.amount = amount;
		this.investmentType = investmentType;
		this.investmentDate = investmentDate;
		this.interestRate = interestRate;
		this.interestStartDate = interestStartDate;
		this.status = status;
		this.closeDate = closeDate;
		this.entryBy = entryBy;
		this.closeBy = closeBy;
		this.lenderName = lenderName;
	}


	@Override
	public String toString() {
		return "Investment [id=" + id + ", lenderId=" + lenderId + ", amount=" + amount + ", investmentType="
				+ investmentType + ", investmentDate=" + investmentDate + ", interestRate=" + interestRate
				+ ", interestStartDate=" + interestStartDate + ", status=" + status + ", closeDate=" + closeDate
				+ ", entryBy=" + entryBy + ", closeBy=" + closeBy + ", lenderName=" + lenderName + "]";
	}


	 	
	
	
}
