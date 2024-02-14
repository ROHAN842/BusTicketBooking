package com.hexaware.fastx.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastx.dto.AuthenticationRequest;
import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.BusSchedule.Amenities;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.UserNotFoundException;
import com.hexaware.fastx.service.IUserService;
import com.hexaware.fastx.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private final IUserService service;

	@Autowired
	public UserRestController(IUserService service) {
	    this.service = service;
	}
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@PostMapping("/login-user")
	public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) {
		logger.info("User logging in");
		return service.loginUser(authenticationRequest);
	}
	
	@PostMapping("/register-user")
	public User registerUser(@RequestBody @Valid UserDTO userDto) {
		logger.info("User details added successfully!!!!");
		return service.registerUser(userDto);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping("/update-user/{userId}")
	public User updateUser(@RequestBody @Valid UserDTO userDto, @PathVariable int userId) throws UserNotFoundException {
		logger.warn("User details updated!!!!");
		return service.updateUserProfile(userDto, userId);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/get-busroute-by-origin-destination/{origin}/{destination}")
	public List<BusRoute> getBusRouteByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
		logger.info("Get Bus Route by origin and destination!!!!");
		return service.searchBusRoutes(origin, destination);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/get-bus-schedule-by-route-id/{routeId}")
	public List<BusSchedule> getAvailableSchedulesById(@PathVariable int routeId) {
		logger.info("Get bus schedule by it's id!!");
		return service.getAvailableSchedules(routeId);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/get-fares-and-amenities-by-schedule-id/{scheduleId}")
	public Map<Integer, Set<Amenities>> getFaresAndAmenities(@PathVariable int scheduleId) {
		logger.info("Get fares and amenities by schedule id");
		return service.getFaresAndAmenities(scheduleId);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/book-your-tickets")
	public Booking bookTickets(@RequestBody BookingDTO bookingDto) {
		logger.info("Booking tickets for the trip!");
		return service.bookTickets(bookingDto);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping("/update-password-by-id/{userId}/{newPassword}")
	public boolean updatePassword(@PathVariable int userId, @PathVariable String newPassword) throws UserNotFoundException {
		logger.warn("User password updated successfully!!!!");
		return service.changePassword(userId, newPassword);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/get-booking-history/{userId}")
	public List<Booking> getBookingHistory(@PathVariable int userId) {
		logger.info("Listing booking history for the user");
		return service.getBookingHistory(userId);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@DeleteMapping("/cancel-booking/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) {
		logger.warn("cancelling booking.");
		return service.cancelBooking(bookingId);
	}

}



