package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name="education_master")
public class Eduaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="education_id")
	private int id; 
	@Column(name="education_name")
	private String name;
	
	
	
	
	
	
	
	
	
	
	
	
	public Eduaction() {
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












	public Eduaction(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}












	@Override
	public String toString() {
		return "Eduaction [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	

}
