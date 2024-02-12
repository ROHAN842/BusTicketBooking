package com.hexaware.fastx.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.BusSchedule.Amenities;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.UserNotFoundException;
import com.hexaware.fastx.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	IUserService service;
	
	@PostMapping("/add-user")
	public User registerUser(@RequestBody @Valid UserDTO userDto) {
	    User user = service.registerUser(userDto);
		return user;
	}
	
	@PutMapping("/update-user/{userId}")
	public User updateUser(@RequestBody @Valid UserDTO userDto, @PathVariable int userId) throws UserNotFoundException {
		return service.updateUserProfile(userDto, userId);
	}
	
	@GetMapping("/get-busroute-by-origin-destination/{origin}/{destination}")
	public List<BusRoute> getBusRouteByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
		return service.searchBusRoutes(origin, destination);
	}
	
	@GetMapping("/get-bus-schedule-by-id/{routeId}")
	public List<BusSchedule> getAvailableSchedulesById(@PathVariable int routeId) {
		return service.getAvailableSchedules(routeId);
	}
	
	@GetMapping("/get-fares-and-amenities-by-schedule-id/{scheduleId}")
	public Map<Integer, Set<Amenities>> getFaresAndAmenities(@PathVariable int scheduleId) {
		return service.getFaresAndAmenities(scheduleId);
	}
	
	@PostMapping("/book-your-tickets")
	public Booking bookTickets(@RequestBody BookingDTO bookingDto) {
		return service.bookTickets(bookingDto);
	}
	
	@PutMapping("/update-password-by-id/{userId}/{newPassword}")
	public boolean updatePassword(@PathVariable int userId, @PathVariable String newPassword) throws UserNotFoundException {
		return service.changePassword(userId, newPassword);
	}

}



