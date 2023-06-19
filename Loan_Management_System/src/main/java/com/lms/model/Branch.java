package com.lms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name="branch_master")
public class Branch {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="branch_id")
	private Long id;
	@Column(name="branch_name")
	private String name;
	@Column(name="address_line1")
	private String addressl1;
	@Column(name="address_line2")
	private String addressl2;
	@Column(name="landmark")
	private String landmark;
	@Column(name="city")
	private String city;
	@Column(name="district")
	private String district;
	@Column(name="state")
	private String state;
	@Column(name="pincode")
	private int pincode;
	@Column(name="branch_opening_date")
	private Date bodate;
	@Column(name="area_id")
	private int aid;
	@Column(name="region_id")
	private int rid;
	@Column(name="cluster_id")
	private int cid;
	@Column(name="zone_id" )
	private int zid;
	@Column(name="branch_status" ,columnDefinition="integer default 1")
	private int status;
	@Column(name="branch_timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@CreationTimestamp
	private Date datetime;
	
	
	
	
	
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getAddressl1() {
		return addressl1;
	}





	public void setAddressl1(String addressl1) {
		this.addressl1 = addressl1;
	}





	public String getAddressl2() {
		return addressl2;
	}





	public void setAddressl2(String addressl2) {
		this.addressl2 = addressl2;
	}





	public String getLandmark() {
		return landmark;
	}





	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}



 




	public Date getBodate() {
		return bodate;
	}





	public void setBodate(Date bodate) {
		this.bodate = bodate;
	}





	public String getCity() {
		return city;
	}





	public void setCity(String city) {
		this.city = city;
	}





	public String getDistrict() {
		return district;
	}





	public void setDistrict(String district) {
		this.district = district;
	}





	public String getState() {
		return state;
	}





	public void setState(String state) {
		this.state = state;
	}





	public int getPincode() {
		return pincode;
	}





	public void setPincode(int pincode) {
		this.pincode = pincode;
	}





	public int getAid() {
		return aid;
	}





	public void setAid(int aid) {
		this.aid = aid;
	}









	public int getRid() {
		return rid;
	}





	public void setRid(int rid) {
		this.rid = rid;
	}





	public int getCid() {
		return cid;
	}





	public void setCid(int cid) {
		this.cid = cid;
	}





	public int getZid() {
		return zid;
	}





	public void setZid(int zid) {
		this.zid = zid;
	}





	public int getStatus() {
		return status;
	}





	public void setStatus(int status) {
		this.status = status;
	}





	public Date getDatetime() {
		return datetime;
	}





	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}



 



 





	public Branch(Long id, String name, String addressl1, String addressl2, String landmark, String city,
			String district, String state, int pincode, Date bodate, int aid, int rid, int cid, int zid, int status,
			Date datetime) {
		super();
		this.id = id;
		this.name = name;
		this.addressl1 = addressl1;
		this.addressl2 = addressl2;
		this.landmark = landmark;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.bodate = bodate;
		this.aid = aid;
		this.rid = rid;
		this.cid = cid;
		this.zid = zid;
		this.status = status;
		this.datetime = datetime;
	}





	@Override
	public String toString() {
		return "Branch [id=" + id + ", name=" + name + ", addressl1=" + addressl1 + ", addressl2=" + addressl2
				+ ", landmark=" + landmark + ", city=" + city + ", district=" + district + ", state=" + state
				+ ", pincode=" + pincode + ", bodate=" + bodate + ", aid=" + aid + ", rid=" + rid + ", cid=" + cid
				+ ", zid=" + zid + ", status=" + status + ", datetime=" + datetime + "]";
	}




 




 



 
	
	
	
}
