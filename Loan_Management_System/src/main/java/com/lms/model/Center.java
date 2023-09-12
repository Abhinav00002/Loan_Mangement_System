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
	private Long bname;
	@Column (name="center_meeting_day")
	private int cmday;
	@Column(name="center_sourced_by")
	private String sourcedby;
	@Column(name="center_meeting_time")
	private int time;
	private String addressl1;
	private String addressl2;
	private String landmark;
	private String state;
	private String pincode;
	private String city;
	private int centerId;
	@Column(name = "center_type", columnDefinition = "INT DEFAULT 1")
	private int centerType;
	private int ceManageBy;
	
	
	
	
	public Center() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getCenterId() {
		return centerId;
	}




	public long getNcid() {
		return ncid;
	}




	public void setNcid(long ncid) {
		this.ncid = ncid;
	}




	public Long getBname() {
		return bname;
	}




	public void setBname(Long bname) {
		this.bname = bname;
	}




	public int getCmday() {
		return cmday;
	}




	public void setCmday(int cmday) {
		this.cmday = cmday;
	}




	public String getSourcedby() {
		return sourcedby;
	}




	public void setSourcedby(String sourcedby) {
		this.sourcedby = sourcedby;
	}




	public int getTime() {
		return time;
	}




	public void setTime(int time) {
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




	public int getCenterType() {
		return centerType;
	}




	public void setCenterType(int centerType) {
		this.centerType = centerType;
	}




	public int getCeManageBy() {
		return ceManageBy;
	}




	public void setCeManageBy(int ceManageBy) {
		this.ceManageBy = ceManageBy;
	}




	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}




	public Center(long ncid, Long bname, int cmday, String sourcedby, int time, String addressl1, String addressl2,
			String landmark, String state, String pincode, String city, int centerId, int centerType, int ceManageBy) {
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
		this.centerId = centerId;
		this.centerType = centerType;
		this.ceManageBy = ceManageBy;
	}




	@Override
	public String toString() {
		return "Center [ncid=" + ncid + ", bname=" + bname + ", cmday=" + cmday + ", sourcedby=" + sourcedby + ", time="
				+ time + ", addressl1=" + addressl1 + ", addressl2=" + addressl2 + ", landmark=" + landmark + ", state="
				+ state + ", pincode=" + pincode + ", city=" + city + ", centerId=" + centerId + ", centerType="
				+ centerType + ", ceManageBy=" + ceManageBy + "]";
	}








 	
	
	
	 	
	
	
	
	
	
}
