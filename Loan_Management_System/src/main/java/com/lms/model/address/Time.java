package com.lms.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="time")
public class Time {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="time_id")
	private int tid;
	 
	private float time;

	
	
	public Time() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getTid() {
		return tid;
	}



	public void setTid(int tid) {
		this.tid = tid;
	}



	public float getTime() {
		return time;
	}



	public void setTime(float time) {
		this.time = time;
	}



	public Time(int tid, float time) {
		super();
		this.tid = tid;
		this.time = time;
	}



	@Override
	public String toString() {
		return "Time [tid=" + tid + ", time=" + time + "]";
	}
	
	
	
}
