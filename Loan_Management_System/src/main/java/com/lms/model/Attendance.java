package com.lms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "attendance_master")
public class Attendance {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Temporal(TemporalType.DATE)
	    private Date attendanceDate;
	    
	    private boolean isHoliday;
	    
	    private boolean isSunday;
	    private int staffId;
		
	    
	    
	    public Attendance() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	     

}
