package com.hexaware.fastx.controller;

import java.math.BigDecimal;
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

import com.hexaware.fastx.dto.BusOperatorDTO;
import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.dto.BusScheduleDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.exception.BookingNotFoundException;
import com.hexaware.fastx.exception.BusRouteNotFoundException;
import com.hexaware.fastx.exception.ScheduleNotFoundException;
import com.hexaware.fastx.exception.SeatUnavailableException;
import com.hexaware.fastx.service.IBusOperatorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/busoperators")
public class BusOperatorRestController {

	@Autowired
	IBusOperatorService service;
	
	@PostMapping("/add-bus-operator")
	public BusOperator registerBusOperator(@RequestBody @Valid BusOperatorDTO busOperatorDto) {
		return service.registerBusOperator(busOperatorDto);
	}
	
	@PostMapping("/add-bus-route")
	public BusRoute addBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto) {
		return service.addBusRoute(busRouteDto);
	}
	
	@PutMapping("/update-bus-route/{routeId}")
	public BusRoute updateBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto, @PathVariable int routeId) throws BusRouteNotFoundException {
		return service.editBusRoute(busRouteDto, routeId);
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
	
	@PutMapping("/edit-bus-schedule/{scheduleId}")
	public BusSchedule updateBusSchedule(@RequestBody @Valid BusScheduleDTO busScheduleDto, @PathVariable int scheduleId) throws ScheduleNotFoundException {
		return service.editBusSchedule(busScheduleDto, scheduleId);
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
	
	@GetMapping("/check-if-seat-available/{scheduleId}")
	public boolean manageSeatAvailability(@PathVariable int scheduleId) throws SeatUnavailableException {
		return service.manageSeatAvailability(scheduleId);
	}
	
	@PutMapping("/refund-cancelled-tickets/{bookingID}")
	public boolean refundCancelledTickets(@PathVariable int bookingID) throws BookingNotFoundException {
		return service.refundCancelledTickets(bookingID);
	}
	
	@PutMapping("/update-bus-fare-for-a-schedule/{fare}/{scheduleId}")
	public boolean updateFaresForSchedule(@PathVariable BigDecimal fare, @PathVariable int scheduleId) throws ScheduleNotFoundException{
		return service.updateFares(scheduleId, fare);
	}
}
