package com.lms.model.address;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id; 
import javax.persistence.Table;
 

 @Entity
@Table(name="state_master")
public class States {
	
	@Id
	 
	 @Column(name="state_id")
	private int id;
	
	
	 @Column(name="state_code")
	private String code;
	 
	 @Column(name="state_name")
	private String name;
	 
	 @Column(name="state_status")
	private String status;
	 
	 @Column(name="state_TIN")
	private int TIN;
	 
	 
	 
	 
	 
	 
	 
	 
	 
	public States() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTIN() {
		return TIN;
	}
	public void setTIN(int tIN) {
		TIN = tIN;
	}

	
 



	@Override
	public String toString() {
		return "States [id=" + id + ", code=" + code + ", name=" + name + ", status=" + status + ", TIN=" + TIN + "]";
	}




	public States(int id, String code, String name, String status, int tIN) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.status = status;
		TIN = tIN;
	}
	

	
	
}
