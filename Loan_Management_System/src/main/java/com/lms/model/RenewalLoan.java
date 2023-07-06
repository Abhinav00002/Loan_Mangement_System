package com.lms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "renewal_master")
public class RenewalLoan {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="loan_id")
	private Integer loanID;
	@Column(name = "sourced_by")
	private Integer sourcedBy;
	@Column(name = "entry_date",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private LocalDate entryDate;
	
	
	
	public RenewalLoan() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getLoanID() {
		return loanID;
	}



	public void setLoanID(Integer loanID) {
		this.loanID = loanID;
	}



	public Integer getSourcedBy() {
		return sourcedBy;
	}



	public void setSourcedBy(Integer sourcedBy) {
		this.sourcedBy = sourcedBy;
	}



	public LocalDate getEntryDate() {
		return entryDate;
	}



	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}



	public RenewalLoan(Integer id, Integer loanID, Integer sourcedBy, LocalDate entryDate) {
		super();
		this.id = id;
		this.loanID = loanID;
		this.sourcedBy = sourcedBy;
		this.entryDate = entryDate;
	}



	@Override
	public String toString() {
		return "RenewalLoan [id=" + id + ", loanID=" + loanID + ", sourcedBy=" + sourcedBy + ", entryDate=" + entryDate
				+ "]";
	}
	
	
	
	

}
