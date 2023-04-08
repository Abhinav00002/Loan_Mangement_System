package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment_mode_master")
public class PaymentMode {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_mode_id")
	private int pmId;
	@Column(name="payment_mode_name")
	
	
	private String pmName;
	public PaymentMode() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
