package com.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="center_master")
public class Center {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ncid;
	@Column(name="branch_name")
	private String bname;
	@Column (name="center_meeting_day")
	private String cmday;
	@Column(name="center_sourced_by")
	private String sourcedby;
	@Column(name="center_meeting_time")
	private String time;
	private String addressl1;
	private String addressl2;
	private String landmark;
	private String state;
	private String pincode;
	private String city;
	
	
	
	
	
	
	public Center() {
		super();
		// TODO Auto-generated constructor stub
	}






	public long getNcid() {
		return ncid;
	}






	public void setNcid(long ncid) {
		this.ncid = ncid;
	}






	public String getBname() {
		return bname;
	}






	public void setBname(String bname) {
		this.bname = bname;
	}






	public String getCmday() {
		return cmday;
	}






	public void setCmday(String cmday) {
		this.cmday = cmday;
	}






	public String getSourcedby() {
		return sourcedby;
	}






	public void setSourcedby(String sourcedby) {
		this.sourcedby = sourcedby;
	}






	public String getTime() {
		return time;
	}






	public void setTime(String time) {
		this.time = time;
	}






	public String getAddressl1() {
		return addressl1;
	}






	public void setAddressl1(String addressl1) {
		this.addressl1 = addressl1;
	}






	public String getAddressl2() {
		return addressl2;
	}






	public void setAddressl2(String addressl2) {
		this.addressl2 = addressl2;
	}






	public String getLandmark() {
		return landmark;
	}






	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}






	public String getState() {
		return state;
	}






	public void setState(String state) {
		this.state = state;
	}






	public String getPincode() {
		return pincode;
	}






	public void setPincode(String pincode) {
		this.pincode = pincode;
	}






	public String getCity() {
		return city;
	}






	public void setCity(String city) {
		this.city = city;
	}






	public Center(long ncid, String bname, String cmday, String sourcedby, String time, String addressl1,
			String addressl2, String landmark, String state, String pincode, String city) {
		super();
		this.ncid = ncid;
		this.bname = bname;
		this.cmday = cmday;
		this.sourcedby = sourcedby;
		this.time = time;
		this.addressl1 = addressl1;
		this.addressl2 = addressl2;
		this.landmark = landmark;
		this.state = state;
		this.pincode = pincode;
		this.city = city;
	}






	@Override
	public String toString() {
		return "CreateCenter [ncid=" + ncid + ", bname=" + bname + ", cmday=" + cmday + ", sourcedby=" + sourcedby
				+ ", time=" + time + ", addressl1=" + addressl1 + ", addressl2=" + addressl2 + ", landmark=" + landmark
				+ ", state=" + state + ", pincode=" + pincode + ", city=" + city + "]";
	}

	
	
	
	
	
	
	
	
}
