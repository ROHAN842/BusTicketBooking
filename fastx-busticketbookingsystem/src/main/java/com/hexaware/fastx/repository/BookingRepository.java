package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.user.userId = :userId")
	List<Booking> findByUserId(int userId);
    
    @Query("SELECT b FROM Booking b WHERE b.busSchedule.scheduleID = :scheduleId")
	List<Booking> findByBusScheduleId(int scheduleId);
}
