package com.lms.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="scheme_master")
public class Scheme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="scheme_id")
    private long id;
	@Column(name = "scheme_name")
	private String schemeName;
	private double loneamount;
	private int emitype;
	private double emi;
	private double tenor;
	private int duetype;
	private double insuranceamount;
	private double pfamount;
	private  double intrestrate;
	private  double irr;
	@Column(name="schemeDate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private LocalDate schemeDate;
	private long  schemeBy;
	
	
	
	
	
	
	
	public Scheme() {
		super();
		// TODO Auto-generated constructor stub
	}







	public long getId() {
		return id;
	}







	public void setId(long id) {
		this.id = id;
	}







	public String getSchemeName() {
		return schemeName;
	}







	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}







	public double getLoneamount() {
		return loneamount;
	}







	public void setLoneamount(double loneamount) {
		this.loneamount = loneamount;
	}







	public int getEmitype() {
		return emitype;
	}







	public void setEmitype(int emitype) {
		this.emitype = emitype;
	}







	public double getEmi() {
		return emi;
	}







	public void setEmi(double emi) {
		this.emi = emi;
	}







	public double getTenor() {
		return tenor;
	}







	public void setTenor(double tenor) {
		this.tenor = tenor;
	}







	public int getDuetype() {
		return duetype;
	}







	public void setDuetype(int duetype) {
		this.duetype = duetype;
	}







	public double getInsuranceamount() {
		return insuranceamount;
	}







	public void setInsuranceamount(double insuranceamount) {
		this.insuranceamount = insuranceamount;
	}







	public double getPfamount() {
		return pfamount;
	}







	public void setPfamount(double pfamount) {
		this.pfamount = pfamount;
	}







	public double getIntrestrate() {
		return intrestrate;
	}







	public void setIntrestrate(double intrestrate) {
		this.intrestrate = intrestrate;
	}







	public double getIrr() {
		return irr;
	}







	public void setIrr(double irr) {
		this.irr = irr;
	}







	public LocalDate getSchemeDate() {
		return schemeDate;
	}







	public void setSchemeDate(LocalDate schemeDate) {
		this.schemeDate = schemeDate;
	}







	public long getSchemeBy() {
		return schemeBy;
	}







	public void setSchemeBy(long schemeBy) {
		this.schemeBy = schemeBy;
	}







	public Scheme(long id, String schemeName, double loneamount, int emitype, double emi, double tenor, int duetype,
			double insuranceamount, double pfamount, double intrestrate, double irr, LocalDate schemeDate,
			long schemeBy) {
		super();
		this.id = id;
		this.schemeName = schemeName;
		this.loneamount = loneamount;
		this.emitype = emitype;
		this.emi = emi;
		this.tenor = tenor;
		this.duetype = duetype;
		this.insuranceamount = insuranceamount;
		this.pfamount = pfamount;
		this.intrestrate = intrestrate;
		this.irr = irr;
		this.schemeDate = schemeDate;
		this.schemeBy = schemeBy;
	}







	@Override
	public String toString() {
		return "Scheme [id=" + id + ", schemeName=" + schemeName + ", loneamount=" + loneamount + ", emitype=" + emitype
				+ ", emi=" + emi + ", tenor=" + tenor + ", duetype=" + duetype + ", insuranceamount=" + insuranceamount
				+ ", pfamount=" + pfamount + ", intrestrate=" + intrestrate + ", irr=" + irr + ", schemeDate="
				+ schemeDate + ", schemeBy=" + schemeBy + "]";
	}
	
	
	
	
	 


 



 
 



 
}
