package com.lms.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="staff_master")
public class Staff {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="staff_id")
	private int id;
	@Column(name="staff_name")
	private String sname;
	@Column(name="staff_father_name")
	private String fname;
	@Column(name="staff_date_of_birth")
	private String dob;
	@Column(name="address_line_1")
	private String addressl1;
	@Column (name="address_line_2")
	private String addressl2;
	@Column(name="landmark")
	private String landmark;
	private String city;
	private String district;
	private String state;
	private int pincode;
	@Column(name="aadhar_c_number")
	private long acnumber;
	@Column(name="pan_c_number")
	private String pcnumber;
	@Column(name="cluster_id")
	private int cid;
	@Column(name="zone_id")
	private int zid;
	@Column(name="region_id")
	private int rid;
	
	
	
	
	
	
	
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}








	public int getId() {
		return id;
	}








	public void setId(int id) {
		this.id = id;
	}








	public String getSname() {
		return sname;
	}








	public void setSname(String sname) {
		this.sname = sname;
	}








	public String getFname() {
		return fname;
	}








	public void setFname(String fname) {
		this.fname = fname;
	}








	public String getDob() {
		return dob;
	}








	public void setDob(String dob) {
		this.dob = dob;
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








	public long getAcnumber() {
		return acnumber;
	}








	public void setAcnumber(long acnumber) {
		this.acnumber = acnumber;
	}








	public String getPcnumber() {
		return pcnumber;
	}








	public void setPcnumber(String pcnumber) {
		this.pcnumber = pcnumber;
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








	public int getRid() {
		return rid;
	}








	public void setRid(int rid) {
		this.rid = rid;
	}








	public Staff(int id, String sname, String fname, String dob, String addressl1, String addressl2, String landmark,
			String city, String district, String state, int pincode, long acnumber, String pcnumber, int cid, int zid,
			int rid) {
		super();
		this.id = id;
		this.sname = sname;
		this.fname = fname;
		this.dob = dob;
		this.addressl1 = addressl1;
		this.addressl2 = addressl2;
		this.landmark = landmark;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
		this.acnumber = acnumber;
		this.pcnumber = pcnumber;
		this.cid = cid;
		this.zid = zid;
		this.rid = rid;
	}








	@Override
	public String toString() {
		return "Staff [id=" + id + ", sname=" + sname + ", fname=" + fname + ", dob=" + dob + ", addressl1=" + addressl1
				+ ", addressl2=" + addressl2 + ", landmark=" + landmark + ", city=" + city + ", district=" + district
				+ ", state=" + state + ", pincode=" + pincode + ", acnumber=" + acnumber + ", pcnumber=" + pcnumber
				+ ", cid=" + cid + ", zid=" + zid + ", rid=" + rid + "]";
	}







 	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
