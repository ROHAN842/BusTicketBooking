package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4

import com.hexaware.fastx.entities.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
<<<<<<< HEAD
	
	@Query(value = "SELECT * FROM booking b where b.userid = ?1", nativeQuery = true)
	List<Booking> findByUserId(int userId);
	
	@Query(value = "SELECT * FROM booking b where b.scheduleid = ?1", nativeQuery = true)
	List<Booking> findByScheduleId(int scheduleID);
=======
    @Query("SELECT b FROM Booking b WHERE b.user.userId = :userId")
	List<Booking> findByUserId(int userId);
    
    @Query("SELECT b FROM Booking b WHERE b.busSchedule.scheduleID = :scheduleId")
	List<Booking> findByBusScheduleId(int scheduleId);
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
}
