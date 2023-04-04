package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="relation_master")
public class Relationships {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="relation_id")
	private int id;
	@Column(name="relation_name")
	private String name;
	
	
	
	
	
	
	
	public Relationships() {
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







	public Relationships(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}







	@Override
	public String toString() {
		return "Relationships [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
	
	

}
