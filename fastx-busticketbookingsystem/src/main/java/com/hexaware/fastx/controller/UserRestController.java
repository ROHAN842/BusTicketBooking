package com.hexaware.fastx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	IUserService service;
	
	@PostMapping("/add-user")
	public User registerUser(@RequestBody UserDTO userDto) {
		return service.registerUser(userDto);
	}
	
	@PutMapping("/update-user")
	public User updateUser(@RequestBody UserDTO userDto) {
		return service.updateUserProfile(userDto);
	}
	
	@GetMapping("/get-busroute-by-origin-destination/{origin}/{destination}")
	public List<BusRoute> getBusRouteByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
		return service.searchBusRoutes(origin, destination);
	}
	
	@GetMapping("/get-bus-schedule-by-id/{routeId}")
	public List<BusSchedule> getAvailableSchedulesById(@PathVariable int routeId) {
		return service.getAvailableSchedules(routeId);
	}
}