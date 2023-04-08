package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="due_master")
public class Due {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="due_id")
	private int id;
	@Column(name="due_name")
	private String type;
	
	
	
	
	public Due() {
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
	
	
	
	
	
	
	
	




	public Due(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}




	@Override
	public String toString() {
		return "Due [id=" + id + ", type=" + type + ", getId()=" + getId() + ", getType()=" + getType()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

	 
}
