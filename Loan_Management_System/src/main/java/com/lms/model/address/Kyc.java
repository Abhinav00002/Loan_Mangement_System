package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="kyc_master")
public class Kyc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="kyc_id")
	private int id;
	@Column(name="kyc_name")
	private String kyctype;
	
	
	
	public Kyc() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getKyctype() {
		return kyctype;
	}



	public void setKyctype(String kyctype) {
		this.kyctype = kyctype;
	}



	public Kyc(int id, String kyctype) {
		super();
		this.id = id;
		this.kyctype = kyctype;
	}



	@Override
	public String toString() {
		return "Kyc [id=" + id + ", kyctype=" + kyctype + "]";
	}
	
	
	
	
}
