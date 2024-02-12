package com.hexaware.fastx.controller;

import java.util.List;

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
import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.BusRouteNotFoundException;
import com.hexaware.fastx.service.IAdminService;
import com.hexaware.fastx.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {
	
	@Autowired
	private IAdminService service;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	Logger logger = LoggerFactory.getLogger(AdminRestController.class);
	
	@PostMapping("/admin-login")
	public String loginAdmin(@RequestBody AuthenticationRequest authenticationRequest) {
		logger.info("Admin loggin in.");
		return service.loginAdmin(authenticationRequest);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{userId}")
	public String deleteUserAccount(@PathVariable int userId) {
		logger.warn("User details deleted!!!!");
		return service.deleteUserAccount(userId);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{operatorId}")
	public String deleteBusOperatorAccount(@PathVariable int operatorId) {
		logger.warn("Bus Operator details deleted!!!!");
		return service.deleteBusOperatorAccount(operatorId);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/get-booking-for-userid/{userId}")
	public List<Booking> getBookingByUserId(@PathVariable int userId) {
		logger.info("Get Bookings by User Id!!!!");
		return service.manageBookedTickets(userId);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getallbusroutes")
	public List<BusRoute> getAllBusRoutes() {
		logger.info("Get All Bus Route details!!!!");
		return service.viewAllBusRoutes();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add-bus-route")
	public BusRoute addBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto) {
		logger.info("Bus Route details added successfully!!!!");
		return service.addBusRoute(busRouteDto);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update-bus-route/{routeId}")
	public BusRoute updateBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto, @PathVariable int routeId) throws BusRouteNotFoundException {
		logger.warn("Bus Route details updated!!!!");
		return service.editBusRoute(busRouteDto, routeId);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete-bus-route-by-Id/{routeId}")
	public String deleteBusRouteById(@PathVariable int routeId) {
		logger.warn("Bus Route details deleted!!!!");
		return service.removeBusRoute(routeId);
	}
	
	@GetMapping("/get-busroute-by-origin-destination/{origin}/{destination}")
	public List<BusRoute> getBusRouteByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
		logger.info("Get Bus Route by origin and destination!!!!");
		return service.searchBusRoutes(origin, destination);
	}
	
	@GetMapping("/get-all-users")
	public List<User> getAllUsers() {
		logger.info("Get All User details!!!!");
		return service.getAllUsers();
	}
	
	@GetMapping("/get-all-bus-operators")
	public List<BusOperator> getAllBusOperators() {
		logger.info("Get All Bus Operator details!!!!");
		return service.getAllBusOperators();
	}
	
	@GetMapping("/get-bus-operator-by-id/{operatorId}")
	public BusOperator getBusOperatorById(@PathVariable int operatorId) {
		logger.info("Get Bus Operator by Id!!!!");
		return service.getBusOperatorById(operatorId);
	}
}
