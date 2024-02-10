package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.Booking.RefundStatus;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query(value = "SELECT * FROM booking b where b.user_id = ?1", nativeQuery = true)
	List<Booking> findByUserId(int userId);
	
	@Query(value = "SELECT * FROM booking b where b.scheduleid = ?1", nativeQuery = true)
	List<Booking> findByScheduleId(int scheduleID);
	
	@Modifying
	@Query(value = "update booking set refund_status = ?1 where bookingid = ?2", nativeQuery = true)
	int updateRefundStatus(RefundStatus refundStatus, int bookingID);
}
