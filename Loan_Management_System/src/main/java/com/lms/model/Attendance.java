package com.lms.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "attendance_master")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_id")
	private Long clientId;

	@Column(name = "marked_by")
	private String markedBy;

	@Column(name = "mark_date")
	private LocalDate markDate;

	private boolean marked;

	private double latitude;

	private double longitude;

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getMarkedBy() {
		return markedBy;
	}

	public void setMarkedBy(String markedBy) {
		this.markedBy = markedBy;
	}

	public LocalDate getMarkDate() {
		return markDate;
	}

	public void setMarkDate(LocalDate markDate) {
		this.markDate = markDate;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Attendance(Long id, Long clientId, String markedBy, LocalDate markDate, boolean marked, double latitude,
			double longitude) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.markedBy = markedBy;
		this.markDate = markDate;
		this.marked = marked;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", clientId=" + clientId + ", markedBy=" + markedBy + ", markDate=" + markDate
				+ ", marked=" + marked + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
