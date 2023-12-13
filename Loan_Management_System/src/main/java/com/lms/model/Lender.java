package com.lms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lender_master")
public class Lender {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lenderId;
	private String lenderName;
	private String  lenderPannumber;
	private String lenderMobnumber;
	
	
	public Lender() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getLenderId() {
		return lenderId;
	}


	public void setLenderId(int lenderId) {
		this.lenderId = lenderId;
	}


	public String getLenderName() {
		return lenderName;
	}


	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}


	public String getLenderPannumber() {
		return lenderPannumber;
	}


	public void setLenderPannumber(String lenderPannumber) {
		this.lenderPannumber = lenderPannumber;
	}


	public String getLenderMobnumber() {
		return lenderMobnumber;
	}


	public void setLenderMobnumber(String lenderMobnumber) {
		this.lenderMobnumber = lenderMobnumber;
	}


	public Lender(int lenderId, String lenderName, String lenderPannumber, String lenderMobnumber) {
		super();
		this.lenderId = lenderId;
		this.lenderName = lenderName;
		this.lenderPannumber = lenderPannumber;
		this.lenderMobnumber = lenderMobnumber;
	}


	@Override
	public String toString() {
		return "Lender [lenderId=" + lenderId + ", lenderName=" + lenderName + ", lenderPannumber=" + lenderPannumber
				+ ", lenderMobnumber=" + lenderMobnumber + "]";
	}
	
	
}
