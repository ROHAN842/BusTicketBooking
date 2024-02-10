package com.hexaware.fastx.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.BusSchedule;

@Repository
public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {

	@Query(
			  value = "SELECT * FROM bus_schedule b where b.routeid = ?1", 
			  nativeQuery = true)

	List<BusSchedule> findByRouteId(int routeId);
	
	@Modifying
	@Query(value = "update bus_schedule set fare = ?1 where scheduleid = ?2", nativeQuery = true)
	int updateFare(BigDecimal fare, int scheduleId);
	
	@Query(
	        value = "SELECT scheduleid, amenities FROM bus_schedule WHERE scheduleid = ?1", 
	        nativeQuery = true)
	List<Object[]> findScheduleIdAndAmenitiesByScheduleId(int scheduleId);
	
}
