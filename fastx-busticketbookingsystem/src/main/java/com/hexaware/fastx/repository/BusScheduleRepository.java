package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.fastx.entities.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {
	@Query(
			  value = "SELECT * FROM bus_schedule b where b.routeid = ?1", 
			  nativeQuery = true)
	List<BusSchedule> findByRouteId(int routeId);
}
