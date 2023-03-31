package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="days")
public class Days {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="days_id")
	private Integer did;
	@Column(name="days_name")
	private String dname;
	
	
	
	public Days() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getDid() {
		return did;
	}



	public void setDid(Integer did) {
		this.did = did;
	}



	public String getDname() {
		return dname;
	}



	public void setDname(String dname) {
		this.dname = dname;
	}



	public Days(Integer did, String dname) {
		super();
		this.did = did;
		this.dname = dname;
	}



	@Override
	public String toString() {
		return "Days [did=" + did + ", dname=" + dname + "]";
	}
	
	
	 	
	

}
