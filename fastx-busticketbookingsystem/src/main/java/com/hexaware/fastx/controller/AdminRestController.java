package com.hexaware.fastx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.service.IAdminService;

<<<<<<< HEAD
import jakarta.validation.Valid;

=======
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
@RestController
@RequestMapping("/api/admins")
public class AdminRestController {
	
	@Autowired
	private IAdminService service;
	
	@DeleteMapping("/delete/{userId}")
	public String deleteUserAccount(@PathVariable int userId) {
		return service.deleteUserAccount(userId);
	}
	
	@DeleteMapping("/delete/{operatorId}")
	public String deleteBusOperatorAccount(@PathVariable int operatorId) {
		return service.deleteBusOperatorAccount(operatorId);
	}
	
	@GetMapping("/get-booking-for-userid/{userId}")
	public List<Booking> getBookingByUserId(@PathVariable int userId) {
		return service.manageBookedTickets(userId);
	}
	
	@GetMapping("/getallbusroutes")
	public List<BusRoute> getAllBusRoutes() {
		return service.viewAllBusRoutes();
	}
	
	@PostMapping("/add-bus-route")
<<<<<<< HEAD
	public BusRoute addBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto) {
=======
	public BusRoute addBusRoute(@RequestBody BusRouteDTO busRouteDto) {
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
		return service.addBusRoute(busRouteDto);
	}
	
	@PutMapping("/update-bus-route")
<<<<<<< HEAD
	public BusRoute updateBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto) {
=======
	public BusRoute updateBusRoute(@RequestBody BusRouteDTO busRouteDto) {
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
		return service.editBusRoute(busRouteDto);
	}
	
	@DeleteMapping("/delete-bus-route-by-Id/{routeId}")
	public String deleteBusRouteById(@PathVariable int routeId) {
		return service.removeBusRoute(routeId);
	}
	
	@GetMapping("/get-busroute-by-origin-destination/{origin}/{destination}")
	public List<BusRoute> getBusRouteByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
		return service.searchBusRoutes(origin, destination);
	}
	
	@GetMapping("/get-all-users")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	@GetMapping("/get-all-bus-operators")
	public List<BusOperator> getAllBusOperators() {
		return service.getAllBusOperators();
	}
	
	@GetMapping("/get-bus-operator-by-id/{operatorId}")
	public BusOperator getBusOperatorById(@PathVariable int operatorId) {
		return service.getBusOperatorById(operatorId);
	}
}
