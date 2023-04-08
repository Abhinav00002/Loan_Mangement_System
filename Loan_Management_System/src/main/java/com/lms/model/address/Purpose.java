package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name="purpose")
public class Purpose {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="purpose_id")
	private int id;
	@Column(name="purpose_name")
	private String name;
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Purpose() {
		super();
		// TODO Auto-generated constructor stub
	}













	public int getId() {
		return id;
	}













	public void setId(int id) {
		this.id = id;
	}













	public String getName() {
		return name;
	}













	public void setName(String name) {
		this.name = name;
	}













	public Purpose(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}













	@Override
	public String toString() {
		return "Purpose [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
