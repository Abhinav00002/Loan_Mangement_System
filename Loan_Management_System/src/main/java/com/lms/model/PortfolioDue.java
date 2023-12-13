package com.lms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "portfolio_due")
public class PortfolioDue {

 
	@Id 
	private int loanId;
	private int branchid;
	private int centerid;
	private String daysName;
	private float time;
	private String customerName;
	private int mobileNumber;
	private int centerType;
	private String addressLine1;
	private String addressLine2;
	private String landmark;
	private String city;
	private String districtName;
	private String stateName;
	private int pincode;
	private String coBorrowerName;
	private int emi;
	private int emipending;
	private int pendinginst;
	 
	private Date duedate;
	
	
	public PortfolioDue() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getLoanId() {
		return loanId;
	}



	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}



	public int getBranchid() {
		return branchid;
	}



	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}



	public int getCenterid() {
		return centerid;
	}



	public void setCenterid(int centerid) {
		this.centerid = centerid;
	}



	public String getDaysName() {
		return daysName;
	}



	public void setDaysName(String daysName) {
		this.daysName = daysName;
	}



	public float getTime() {
		return time;
	}



	public void setTime(float time) {
		this.time = time;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public int getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public int getCenterType() {
		return centerType;
	}



	public void setCenterType(int centerType) {
		this.centerType = centerType;
	}



	public String getAddressLine1() {
		return addressLine1;
	}



	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}



	public String getAddressLine2() {
		return addressLine2;
	}



	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}



	public String getLandmark() {
		return landmark;
	}



	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDistrictName() {
		return districtName;
	}



	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}



	public String getStateName() {
		return stateName;
	}



	public void setStateName(String stateName) {
		this.stateName = stateName;
	}



	public int getPincode() {
		return pincode;
	}



	public void setPincode(int pincode) {
		this.pincode = pincode;
	}



	public String getCoBorrowerName() {
		return coBorrowerName;
	}



	public void setCoBorrowerName(String coBorrowerName) {
		this.coBorrowerName = coBorrowerName;
	}



	public int getEmi() {
		return emi;
	}



	public void setEmi(int emi) {
		this.emi = emi;
	}



	public int getEmipending() {
		return emipending;
	}



	public void setEmipending(int emipending) {
		this.emipending = emipending;
	}



	public int getPendinginst() {
		return pendinginst;
	}



	public void setPendinginst(int pendinginst) {
		this.pendinginst = pendinginst;
	}


	

	public Date getDuedate() {
		return duedate;
	}



	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}



	public PortfolioDue(int loanId, int branchid, int centerid, String daysName, float time, String customerName,
			int mobileNumber, int centerType, String addressLine1, String addressLine2, String landmark, String city,
			String districtName, String stateName, int pincode, String coBorrowerName, int emi, int emipending,
			int pendinginst, Date duedate) {
		super();
		this.loanId = loanId;
		this.branchid = branchid;
		this.centerid = centerid;
		this.daysName = daysName;
		this.time = time;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.centerType = centerType;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.landmark = landmark;
		this.city = city;
		this.districtName = districtName;
		this.stateName = stateName;
		this.pincode = pincode;
		this.coBorrowerName = coBorrowerName;
		this.emi = emi;
		this.emipending = emipending;
		this.pendinginst = pendinginst;
		this.duedate = duedate;
	}



	@Override
	public String toString() {
		return "PortfolioDue [loanId=" + loanId + ", branchid=" + branchid + ", centerid=" + centerid + ", daysName="
				+ daysName + ", time=" + time + ", customerName=" + customerName + ", mobileNumber=" + mobileNumber
				+ ", centerType=" + centerType + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", landmark=" + landmark + ", city=" + city + ", districtName=" + districtName + ", stateName="
				+ stateName + ", pincode=" + pincode + ", coBorrowerName=" + coBorrowerName + ", emi=" + emi
				+ ", emipending=" + emipending + ", pendinginst=" + pendinginst + ", duedate=" + duedate + "]";
	}



	 	
	
	
}
