package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	List<Booking> findByUserId(int userId);
	
	List<Booking> findByScheduleId(int scheduleId);
}
