package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="loan_master")
public class LoanCreation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="loan_id")
	private long id;
	
	@Column(name="lead_id")
	private String leadid;
	private String branchname;
	private String centername;
	private String name;
	@Column(name="spouse_name")
	private String sname;
	private String scheme;
	private String purpose;
	@Column(name="disbursement_date")
	private Date ddate;
	@Column(name="borower_co_borower_relation")
	private String cbrelation;
	private String selfincome;
	@Column(name="spouse_income")
	private String sincome;
	@Column(name="other_income")
	private String oincome;
	@Column(name="home_expences")
	private String hexpences;
	@Column(name="bussiness_expences")
	private String bexpences;
	@Column(name="lone_expences")
	private String lexpences;
	private String sourcedby;
	
	
	
	
	
	
	
	public LoanCreation() {
		super();
		// TODO Auto-generated constructor stub
	}







	public long getId() {
		return id;
	}







	public void setId(long id) {
		this.id = id;
	}







	public String getLeadid() {
		return leadid;
	}







	public void setLeadid(String leadid) {
		this.leadid = leadid;
	}







	public String getBranchname() {
		return branchname;
	}







	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}







	public String getCentername() {
		return centername;
	}







	public void setCentername(String centername) {
		this.centername = centername;
	}







	public String getName() {
		return name;
	}







	public void setName(String name) {
		this.name = name;
	}







	public String getSname() {
		return sname;
	}







	public void setSname(String sname) {
		this.sname = sname;
	}







	public String getScheme() {
		return scheme;
	}







	public void setScheme(String scheme) {
		this.scheme = scheme;
	}







	public String getPurpose() {
		return purpose;
	}







	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}







	public Date getDdate() {
		return ddate;
	}







	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}







	public String getCbrelation() {
		return cbrelation;
	}







	public void setCbrelation(String cbrelation) {
		this.cbrelation = cbrelation;
	}







	public String getSelfincome() {
		return selfincome;
	}







	public void setSelfincome(String selfincome) {
		this.selfincome = selfincome;
	}







	public String getSincome() {
		return sincome;
	}







	public void setSincome(String sincome) {
		this.sincome = sincome;
	}







	public String getOincome() {
		return oincome;
	}







	public void setOincome(String oincome) {
		this.oincome = oincome;
	}







	public String getHexpences() {
		return hexpences;
	}







	public void setHexpences(String hexpences) {
		this.hexpences = hexpences;
	}







	public String getBexpences() {
		return bexpences;
	}







	public void setBexpences(String bexpences) {
		this.bexpences = bexpences;
	}







	public String getLexpences() {
		return lexpences;
	}







	public void setLexpences(String lexpences) {
		this.lexpences = lexpences;
	}







	public String getSourcedby() {
		return sourcedby;
	}







	public void setSourcedby(String sourcedby) {
		this.sourcedby = sourcedby;
	}







	public LoanCreation(long id, String leadid, String branchname, String centername, String name, String sname,
			String scheme, String purpose, Date ddate, String cbrelation, String selfincome, String sincome,
			String oincome, String hexpences, String bexpences, String lexpences, String sourcedby) {
		super();
		this.id = id;
		this.leadid = leadid;
		this.branchname = branchname;
		this.centername = centername;
		this.name = name;
		this.sname = sname;
		this.scheme = scheme;
		this.purpose = purpose;
		this.ddate = ddate;
		this.cbrelation = cbrelation;
		this.selfincome = selfincome;
		this.sincome = sincome;
		this.oincome = oincome;
		this.hexpences = hexpences;
		this.bexpences = bexpences;
		this.lexpences = lexpences;
		this.sourcedby = sourcedby;
	}







	@Override
	public String toString() {
		return "LoanCreation [id=" + id + ", leadid=" + leadid + ", branchname=" + branchname + ", centername="
				+ centername + ", name=" + name + ", sname=" + sname + ", scheme=" + scheme + ", purpose=" + purpose
				+ ", ddate=" + ddate + ", cbrelation=" + cbrelation + ", selfincome=" + selfincome + ", sincome="
				+ sincome + ", oincome=" + oincome + ", hexpences=" + hexpences + ", bexpences=" + bexpences
				+ ", lexpences=" + lexpences + ", sourcedby=" + sourcedby + "]";
	}
	
	
	
	
	
	
	

}
