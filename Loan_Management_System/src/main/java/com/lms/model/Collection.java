package com.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "collection_master")
public class Collection {
	
	@Id
	@Column(name = "collection_id")
	private int id;
	
	

}
