package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="region_master")
public class Region {
	
	@Id
	@Column(name="region_id")
	private int id;
	
	@Column(name="region_name")
	private String rname;

	
	
	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getRname() {
		return rname;
	}



	public void setRname(String rname) {
		this.rname = rname;
	}



	public Region(int id, String rname) {
		super();
		this.id = id;
		this.rname = rname;
	}



	@Override
	public String toString() {
		return "Region [id=" + id + ", rname=" + rname + "]";
	}

	
	 
	 
}
