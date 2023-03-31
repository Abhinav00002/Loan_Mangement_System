package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id; 
import javax.persistence.Table;


@Entity
@Table(name="district_master")
public class District {

	
	@Id
	@Column(name="district_id")
	private int id;
	
	 
	@Column(name="state_id")
	private int stateId;
	
	@Column(name="district_name")
	private String name;
	
	@Column(name="district_status")
	private int status;
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	public District() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public District(int id, int stateId, String name, int status) {
		super();
		this.id = id;
		this.stateId = stateId;
		this.name = name;
		this.status = status;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", stateId=" + stateId + ", name=" + name + ", status=" + status + "]";
	}

 	
	
	
}
