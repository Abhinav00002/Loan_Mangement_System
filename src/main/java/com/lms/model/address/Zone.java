package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zone_master")
public class Zone {
	
	@Id
	@Column(name="zone_id")
	private int id;
	@Column(name="zone_name")
	private String zname;
	
	
	
	
	public Zone() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getZname() {
		return zname;
	}




	public void setZname(String zname) {
		this.zname = zname;
	}




	public Zone(int id, String zname) {
		super();
		this.id = id;
		this.zname = zname;
	}




	@Override
	public String toString() {
		return "Zone [id=" + id + ", zname=" + zname + "]";
	}
	
	
	
	

}
