package com.lms.model.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="remark_type")
public class RemarkType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int remarkId;
	private String remark;
	
	
	public RemarkType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getRemarkId() {
		return remarkId;
	}


	public void setRemarkId(int remarkId) {
		this.remarkId = remarkId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public RemarkType(int remarkId, String remark) {
		super();
		this.remarkId = remarkId;
		this.remark = remark;
	}


	@Override
	public String toString() {
		return "RemarkType [remarkId=" + remarkId + ", remark=" + remark + "]";
	}
	
	
}
