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
@Table(name="staff_master")
public class Staff {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="staff_id")
	private int id;
	@Column(name="staff_name")
	private String sname;
	@Column(name="staff_father_name")
	private String fname;
	@Column(name="staff_date_of_birth")
	private String dob;
	private String email;
	private String contactNo;
	@Column(name="address_line_1")
	private String addressl1;
	@Column (name="address_line_2")
	private String addressl2;
	@Column(name="landmark")
	private String landmark;
	private String city;
	private String district;
	private String state;
	@Column(name="branch_Id")
	private String bId;
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
	@Column(name="staff_joinning_date")
	private Date sJoinDate;
	@Column(name="staff_status" ,columnDefinition="integer default 1")
	private int status;
	@Column(name="staff_left_date")
	private LocalDate sLeftDate;
	private float salary;
	@Column(name = "`rank`")
	private int rank;
	
	
	
	
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





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getContactNo() {
		return contactNo;
	}





	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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





	public String getbId() {
		return bId;
	}





	public void setbId(String bId) {
		this.bId = bId;
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





	public Date getsJoinDate() {
		return sJoinDate;
	}





	public void setsJoinDate(Date sJoinDate) {
		this.sJoinDate = sJoinDate;
	}





	public int getStatus() {
		return status;
	}





	public void setStatus(int status) {
		this.status = status;
	}





	public LocalDate getsLeftDate() {
		return sLeftDate;
	}





	public void setsLeftDate(LocalDate leftDate) {
		this.sLeftDate = leftDate;
	}





	public float getSalary() {
		return salary;
	}





	public void setSalary(float salary) {
		this.salary = salary;
	}




	
	
	

	public int getRank() {
		return rank;
	}





	public void setRank(int rank) {
		this.rank = rank;
	}





	public Staff(int id, String sname, String fname, String dob, String email, String contactNo, String addressl1,
			String addressl2, String landmark, String city, String district, String state, String bId, int pincode,
			long acnumber, String pcnumber, int cid, int zid, int rid, Date sJoinDate, int status, LocalDate sLeftDate,
			float salary, int rank) {
		super();
		this.id = id;
		this.sname = sname;
		this.fname = fname;
		this.dob = dob;
		this.email = email;
		this.contactNo = contactNo;
		this.addressl1 = addressl1;
		this.addressl2 = addressl2;
		this.landmark = landmark;
		this.city = city;
		this.district = district;
		this.state = state;
		this.bId = bId;
		this.pincode = pincode;
		this.acnumber = acnumber;
		this.pcnumber = pcnumber;
		this.cid = cid;
		this.zid = zid;
		this.rid = rid;
		this.sJoinDate = sJoinDate;
		this.status = status;
		this.sLeftDate = sLeftDate;
		this.salary = salary;
		this.rank = rank;
	}





	@Override
	public String toString() {
		return "Staff [id=" + id + ", sname=" + sname + ", fname=" + fname + ", dob=" + dob + ", email=" + email
				+ ", contactNo=" + contactNo + ", addressl1=" + addressl1 + ", addressl2=" + addressl2 + ", landmark="
				+ landmark + ", city=" + city + ", district=" + district + ", state=" + state + ", bId=" + bId
				+ ", pincode=" + pincode + ", acnumber=" + acnumber + ", pcnumber=" + pcnumber + ", cid=" + cid
				+ ", zid=" + zid + ", rid=" + rid + ", sJoinDate=" + sJoinDate + ", status=" + status + ", sLeftDate="
				+ sLeftDate + ", salary=" + salary + ", rank=" + rank + "]";
	}




 

 	
	
	
 		
	
	 	
	
	
	
	 	
}
