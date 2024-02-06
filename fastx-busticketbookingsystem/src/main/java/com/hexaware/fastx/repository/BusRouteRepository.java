package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.BusRoute;
@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
	
	@Query(
			  value = "SELECT * FROM bus_route b where b.origin = ?1 and b.destination = ?2", 
			  nativeQuery = true)

	List<BusRoute> getBusRoutesByOriginAndDestination(String origin, String destination);
}
