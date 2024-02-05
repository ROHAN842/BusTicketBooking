package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.BusSchedule;
@Repository
public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {
	List<BusSchedule> findByRouteId(int routeId);
}
