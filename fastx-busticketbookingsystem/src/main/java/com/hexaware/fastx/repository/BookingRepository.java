package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.fastx.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query(value = "SELECT * FROM booking b where b.userid = ?1", nativeQuery = true)
	List<Booking> findByUserId(int userId);
	
	@Query(value = "SELECT * FROM booking b where b.scheduleid = ?1", nativeQuery = true)
	List<Booking> findByScheduleId(int scheduleID);
}
