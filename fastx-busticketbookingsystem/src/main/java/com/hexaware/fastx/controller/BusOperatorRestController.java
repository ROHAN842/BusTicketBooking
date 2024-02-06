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
import com.hexaware.fastx.dto.BusScheduleDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.service.IBusOperatorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/busoperators")
public class BusOperatorRestController {

	@Autowired
	IBusOperatorService service;
	
	@PostMapping("/add-bus-route")
	public BusRoute addBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto) {
		return service.addBusRoute(busRouteDto);
	}
	
	@PutMapping("/update-bus-route")
	public BusRoute updateBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto) {
		return service.editBusRoute(busRouteDto);
	}
	
	@DeleteMapping("/delete-bus-route-by-id/{routeId}")
	public String deleteBusRoute(@PathVariable int routeId) {
		return service.removeBusRoute(routeId);
	}
	
	@GetMapping("/get-busroute-by-origin-destination/{origin}/{destination}")
	public List<BusRoute> getBusRouteByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
		return service.searchBusRoutes(origin, destination);
	}
	
	@PostMapping("/add-bus-schedule")
	public BusSchedule addBusSchedule(@RequestBody @Valid BusScheduleDTO busScheduleDto) {
		return service.addBusSchedule(busScheduleDto);
	}
	
	@PutMapping("/edit-bus-schedule")
	public BusSchedule updateBusSchedule(@RequestBody @Valid BusScheduleDTO busScheduleDto) {
		return service.editBusSchedule(busScheduleDto);
	}
	
	@DeleteMapping("/delete-bus-schedule-by-id/{scheduleId}")
	public String deleteBusScheduleById(@PathVariable int scheduleId) {
		return service.removeBusSchedule(scheduleId);
	}
	
	@GetMapping("/get-bookings-by-scheduleId/{scheduleId}")
	public List<Booking> getBookingByScheduleId(@PathVariable int scheduleId) {
		return service.viewBookedTickets(scheduleId);
	}
	
	@GetMapping("/get-bookings-by-userId/{userId}")
	public List<Booking> getBookingByUserId(@PathVariable int userId) {
		return service.getBookingHistory(userId);
	}
}
