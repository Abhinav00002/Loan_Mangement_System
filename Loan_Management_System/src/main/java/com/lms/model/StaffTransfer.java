package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="staff_transfer")
public class StaffTransfer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="staff_id")
	private int staffId;
	private Date fromdate;
	private Date todate;
	@Column(name = "live_status")
	private int liveStatus;
	@Column (name = "entry_by")
	private int entryBy;
	@Column(name = "entry_date")
	private Date entryDate;
	
	
	public StaffTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getStaffId() {
		return staffId;
	}


	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}


	public Date getFromdate() {
		return fromdate;
	}


	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}


	public Date getTodate() {
		return todate;
	}


	public void setTodate(Date todate) {
		this.todate = todate;
	}


	public int getLiveStatus() {
		return liveStatus;
	}


	public void setLiveStatus(int liveStatus) {
		this.liveStatus = liveStatus;
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


	
	
	
	
	
	
	public StaffTransfer(int id, int staffId, Date fromdate, Date todate, int liveStatus, int entryBy, Date entryDate) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.fromdate = fromdate;
		this.todate = todate;
		this.liveStatus = liveStatus;
		this.entryBy = entryBy;
		this.entryDate = entryDate;
	}


	@Override
	public String toString() {
		return "StaffTransfer [id=" + id + ", staffId=" + staffId + ", fromdate=" + fromdate + ", todate=" + todate
				+ ", liveStatus=" + liveStatus + ", entryBy=" + entryBy + ", entryDate=" + entryDate + "]";
	}
	
	
	
}
