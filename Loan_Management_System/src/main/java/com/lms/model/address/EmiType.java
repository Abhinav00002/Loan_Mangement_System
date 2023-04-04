package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emi_master")
public class EmiType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emi_id")
	private int id;
	@Column(name="emi_type")
	private String type;
	
	
	
	
	
	
	public EmiType() {
		super();
		// TODO Auto-generated constructor stub
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
	}






	public String getType() {
		return type;
	}






	public void setType(String type) {
		this.type = type;
	}






	public EmiType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}






	@Override
	public String toString() {
		return "EmiType [id=" + id + ", type=" + type + "]";
	}

	
	
	
}
