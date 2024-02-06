package com.hexaware.fastx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
=======
import org.springframework.stereotype.Repository;
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4

import com.hexaware.fastx.entities.BusSchedule;
@Repository
public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {
<<<<<<< HEAD
	@Query(
			  value = "SELECT * FROM bus_schedule b where b.routeid = ?1", 
			  nativeQuery = true)
=======
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
	List<BusSchedule> findByRouteId(int routeId);
}
