
package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="customer_master")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int cid; 
	@Column(name="customer_name")
	private String cname;
	@Column(name="fname_hname")
	private String fname;
	@Column (name="mobile_number")
	private String mobnumber;
	@Column(name="date_of_birth")
	private Date dob;
	private String education;
	@Column (name="addresss_line1")
	private String addl1;
	@Column(name="address_line2")
	private String addl2;
	private String landmark;
	private String city;
	private String district;
	private String state;
	private int pincode;
	@Column(name="pan_number")
	private String pannum;
	@Column(name="kyc_name")
	private String kycname;
	@Column(name="kyc_number")
	private String kycnum;
	private String sourcedBy;
	
	@Column(name="entry_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date entrydate;
	private String entryby;
	private int caution;
	private String voterid;
	
	
	
	
	
	
	
	public String getVoterid() {
		return voterid;
	}








	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}








	public int getCaution() {
		return caution;
	}








	public void setCaution(int caution) {
		this.caution = caution;
	}








	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}








	public int getCid() {
		return cid;
	}








	public void setCid(int cid) {
		this.cid = cid;
	}








	public String getCname() {
		return cname;
	}








	public void setCname(String cname) {
		this.cname = cname;
	}








	public String getFname() {
		return fname;
	}








	public void setFname(String fname) {
		this.fname = fname;
	}








	public String getMobnumber() {
		return mobnumber;
	}








	public void setMobnumber(String mobnumber) {
		this.mobnumber = mobnumber;
	}








	public Date getDob() {
		return dob;
	}








	public void setDob(Date dob) {
		this.dob = dob;
	}








	public String getEducation() {
		return education;
	}








	public void setEducation(String education) {
		this.education = education;
	}








	public String getAddl1() {
		return addl1;
	}








	public void setAddl1(String addl1) {
		this.addl1 = addl1;
	}








	public String getAddl2() {
		return addl2;
	}








	public void setAddl2(String addl2) {
		this.addl2 = addl2;
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








	public String getDistrict() {
		return district;
	}








	public void setDistrict(String string) {
		this.district = string;
	}








	public String getState() {
		return state;
	}








	public void setState(String string) {
		this.state = string;
	}








	public int getPincode() {
		return pincode;
	}








	public void setPincode(int pincode) {
		this.pincode = pincode;
	}








	public String getPannum() {
		return pannum;
	}








	public void setPannum(String pannum) {
		this.pannum = pannum;
	}








	public String getKycname() {
		return kycname;
	}








	public void setKycname(String kycname) {
		this.kycname = kycname;
	}








	public String getKycnum() {
		return kycnum;
	}








	public void setKycnum(String kycnum) {
		this.kycnum = kycnum;
	}








	public String getSourcedBy() {
		return sourcedBy;
	}








	public void setSourcedBy(String string) {
		this.sourcedBy = string;
	}








	public Date getEntrydate() {
		return entrydate;
	}








	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}








	public String getEntryby() {
		return entryby;
	}








	public void setEntryby(String string) {
		this.entryby = string;
	}








	public Customer(int cid, String cname, String fname, String mobnumber, Date dob, String education, String addl1,
			String addl2, String landmark, String city, String district, String state, int pincode, String pannum,
			String kycname, String kycnum, String sourcedBy, Date entrydate, String entryby, int caution,
			String voterid) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.fname = fname;
		this.mobnumber = mobnumber;
		this.dob = dob;
		this.education = education;
		this.addl1 = addl1;
		this.addl2 = addl2;
		this.landmark = landmark;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.pannum = pannum;
		this.kycname = kycname;
		this.kycnum = kycnum;
		this.sourcedBy = sourcedBy;
		this.entrydate = entrydate;
		this.entryby = entryby;
		this.caution = caution;
		this.voterid = voterid;
	}








	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", fname=" + fname + ", mobnumber=" + mobnumber + ", dob="
				+ dob + ", education=" + education + ", addl1=" + addl1 + ", addl2=" + addl2 + ", landmark=" + landmark
				+ ", city=" + city + ", district=" + district + ", state=" + state + ", pincode=" + pincode
				+ ", pannum=" + pannum + ", kycname=" + kycname + ", kycnum=" + kycnum + ", sourcedBy=" + sourcedBy
				+ ", entrydate=" + entrydate + ", entryby=" + entryby + ", caution=" + caution + ", voterid=" + voterid
				+ "]";
	}






 



 

	 	
}
