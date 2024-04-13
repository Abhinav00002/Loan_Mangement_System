package com.lms.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LoanDTO {
	@Id
	 
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
	private LocalDate ddate;
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
	 @Column(name="meetingDay" )
	 
	private int meetingDay; 
	private LocalDate meetingDate; 
	private int status;
	
	 private String sourcedByName ;
	    private String meetingDays ;
	    private String branchNames ;
	    private String purposeName;
	    private String relationName;
	    private String schemeName;
		
	    
	    
	
	
	
	 


    public LoanDTO() {
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









	public LocalDate getDdate() {
		return ddate;
	}









	public void setDdate(LocalDate ddate) {
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









	public int getMeetingDay() {
		return meetingDay;
	}









	public void setMeetingDay(int meetingDay) {
		this.meetingDay = meetingDay;
	}









	public LocalDate getMeetingDate() {
		return meetingDate;
	}









	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}









	public int getStatus() {
		return status;
	}









	public void setStatus(int status) {
		this.status = status;
	}









	public String getSourcedByName() {
		return sourcedByName;
	}









	public void setSourcedByName(String sourcedByName) {
		this.sourcedByName = sourcedByName;
	}









	public String getMeetingDays() {
		return meetingDays;
	}









	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}









	public String getBranchNames() {
		return branchNames;
	}









	public void setBranchNames(String branchNames) {
		this.branchNames = branchNames;
	}









	public String getPurposeName() {
		return purposeName;
	}









	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}









	public String getRelationName() {
		return relationName;
	}









	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}









	public String getSchemeName() {
		return schemeName;
	}









	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}









	@Override
	public String toString() {
		return "LoanDTO [id=" + id + ", leadid=" + leadid + ", branchname=" + branchname + ", centername=" + centername
				+ ", name=" + name + ", sname=" + sname + ", scheme=" + scheme + ", purpose=" + purpose + ", ddate="
				+ ddate + ", cbrelation=" + cbrelation + ", selfincome=" + selfincome + ", sincome=" + sincome
				+ ", oincome=" + oincome + ", hexpences=" + hexpences + ", bexpences=" + bexpences + ", lexpences="
				+ lexpences + ", sourcedby=" + sourcedby + ", meetingDay=" + meetingDay + ", meetingDate=" + meetingDate
				+ ", status=" + status + ", sourcedByName=" + sourcedByName + ", meetingDays=" + meetingDays + ", branchNames="
				+ branchNames + ", purposeName=" + purposeName + ", relationName=" + relationName + ", schemeName="
				+ schemeName + "]";
	}









	public LoanDTO(long id, int leadid, int branchname, int centername, String name, String sname, long scheme,
			int purpose, LocalDate ddate, int cbrelation, String selfincome, String sincome, String oincome,
			String hexpences, String bexpences, String lexpences, int sourcedby, int meetingDay, LocalDate meetingDate,
			int status, String sourcedByName, String meetingDays, String branchNames, String purposeName,
			String relationName, String schemeName) {
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
		this.meetingDay = meetingDay;
		this.meetingDate = meetingDate;
		this.status = status;
		sourcedByName = sourcedByName;
		this.meetingDays = meetingDays;
		branchNames = branchNames;
		this.purposeName = purposeName;
		this.relationName = relationName;
		this.schemeName = schemeName;
	}

 }
