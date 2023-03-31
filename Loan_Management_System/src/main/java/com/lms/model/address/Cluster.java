package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cluster_master")
public class Cluster {
	
	@Id
	@Column(name="cluster_id")
	private int id;
	
	@Column(name="cluster_name")
	private String cname;

	public Cluster() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}




	public Cluster(int id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}




	@Override
	public String toString() {
		return "Cluster [id=" + id + ", cname=" + cname + "]";
	}

	
	
}
