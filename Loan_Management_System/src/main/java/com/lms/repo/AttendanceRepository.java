package com.lms.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.model.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	boolean existsByMarkDateAndClientId(LocalDate markDate, Long clientId);

	@Query(value = "select a.id, a.client_id, a.marked_by, a.mark_date, a.marked, a.latitude, a.longitude from attendance_master a where a.client_id=:clientId ", nativeQuery = true)
	public List<Attendance> findByClientId(Long clientId);

}
