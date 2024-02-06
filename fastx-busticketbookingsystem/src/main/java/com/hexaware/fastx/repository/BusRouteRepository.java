package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4

import com.hexaware.fastx.entities.BusRoute;
@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
<<<<<<< HEAD
	
	@Query(
			  value = "SELECT * FROM bus_route b where b.origin = ?1 and b.destination = ?2", 
			  nativeQuery = true)
=======

	@Query("select b from Bus_Route b where b.origin = ?1 and b.destination = ?2")
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
	List<BusRoute> getBusRoutesByOriginAndDestination(String origin, String destination);
}
