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
@Table(name="loan_master")
public class LoanCreation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="loan_id")
	private long id;
	
	@Column(name="lead_id")
	private int leadid;
	private int branchname;
	private int centername;
	private String name;
	@Column(name="spouse_name")
	private String sname;
	private long scheme;
	private int purpose;
	@Column(name="disbursement_date")
	private Date ddate;
	@Column(name="borower_co_borower_relation")
	private int cbrelation;
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
	private int sourcedby;
	 @Column(name="meetingDate" )
	 
	private Date meetingDate;
	
	
	
	
	
	
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










	public int getLeadid() {
		return leadid;
	}










	public void setLeadid(int leadid) {
		this.leadid = leadid;
	}










	public int getBranchname() {
		return branchname;
	}










	public void setBranchname(int branchname) {
		this.branchname = branchname;
	}










	public int getCentername() {
		return centername;
	}










	public void setCentername(int centername) {
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










	public long getScheme() {
		return scheme;
	}










	public void setScheme(long scheme) {
		this.scheme = scheme;
	}










	public int getPurpose() {
		return purpose;
	}










	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}










	public Date getDdate() {
		return ddate;
	}










	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}










	public int getCbrelation() {
		return cbrelation;
	}










	public void setCbrelation(int cbrelation) {
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










	public int getSourcedby() {
		return sourcedby;
	}










	public void setSourcedby(int sourcedby) {
		this.sourcedby = sourcedby;
	}










	public Date getMeetingDate() {
		return meetingDate;
	}










	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}










	public LoanCreation(long id, int leadid, int branchname, int centername, String name, String sname, long scheme,
			int purpose, Date ddate, int cbrelation, String selfincome, String sincome, String oincome,
			String hexpences, String bexpences, String lexpences, int sourcedby, Date meetingDate) {
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
		this.meetingDate = meetingDate;
	}










	@Override
	public String toString() {
		return "LoanCreation [id=" + id + ", leadid=" + leadid + ", branchname=" + branchname + ", centername="
				+ centername + ", name=" + name + ", sname=" + sname + ", scheme=" + scheme + ", purpose=" + purpose
				+ ", ddate=" + ddate + ", cbrelation=" + cbrelation + ", selfincome=" + selfincome + ", sincome="
				+ sincome + ", oincome=" + oincome + ", hexpences=" + hexpences + ", bexpences=" + bexpences
				+ ", lexpences=" + lexpences + ", sourcedby=" + sourcedby + ", meetingDate=" + meetingDate + "]";
	}






 
}
