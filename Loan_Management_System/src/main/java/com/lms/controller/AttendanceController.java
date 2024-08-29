package com.lms.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.model.Attendance;
import com.lms.repo.AttendanceRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@GetMapping("/list")
	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}

	@GetMapping("/byid/{id}")
	public List<Attendance> getAttendanceById(@PathVariable Long id) {
		return attendanceRepository.findByClientId(id);
	}

	@PostMapping("/mark/")
	public ResponseEntity<Map<String, String>> saveAttendance(@RequestBody Attendance attendance) {
		LocalDate markDate = attendance.getMarkDate();
		Long clientId = attendance.getClientId();
		boolean isAttendanceMarked = attendanceRepository.existsByMarkDateAndClientId(markDate, clientId);

		if (isAttendanceMarked) {
			return ResponseEntity.badRequest()
					.body(Collections.singletonMap("message", "Attendance already marked for this date"));
		} else {
			Attendance savedAttendance = attendanceRepository.save(attendance);
			Map<String, String> response = Collections.singletonMap("message", "Attendance marked successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
	}

}
