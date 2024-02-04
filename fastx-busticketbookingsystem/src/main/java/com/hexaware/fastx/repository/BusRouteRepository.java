package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.fastx.entities.BusRoute;

public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
	
	@Query("select b from Bus_Route b where b.origin = ?1 and b.destination = ?2")
	List<BusRoute> getBusRoutesByOriginAndDestination(String origin, String destination);
}
