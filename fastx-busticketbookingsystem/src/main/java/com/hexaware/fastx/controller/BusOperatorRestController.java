package com.hexaware.fastx.controller;

import java.math.BigDecimal;
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
import com.hexaware.fastx.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/busoperators")
public class BusOperatorRestController {

	@Autowired
	IBusOperatorService service;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	Logger logger = LoggerFactory.getLogger(BusOperatorRestController.class);
	
	@PostMapping("/login-bus-operator")
	public String loginBusOperator(@RequestBody AuthenticationRequest authenticationRequest) {
		logger.info("Bus Operator logging in");
		return service.loginBusOperator(authenticationRequest);
	}
	
	@PostMapping("/register-bus-operator")
	public BusOperator registerBusOperator(@RequestBody @Valid BusOperatorDTO busOperatorDto) {
		logger.info("Bus Operator details added successfully!!!!");
		return service.registerBusOperator(busOperatorDto);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@PostMapping("/add-bus-route")
	public BusRoute addBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto) {
		logger.info("Bus Route details added successfully!!!!");
		return service.addBusRoute(busRouteDto);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@PutMapping("/update-bus-route/{routeId}")
	public BusRoute updateBusRoute(@RequestBody @Valid BusRouteDTO busRouteDto, @PathVariable int routeId) throws BusRouteNotFoundException {
		logger.warn("Bus Route details updated!!!!");
		return service.editBusRoute(busRouteDto, routeId);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@DeleteMapping("/delete-bus-route-by-id/{routeId}")
	public String deleteBusRoute(@PathVariable int routeId) {
		logger.warn("Bus Route details deleted!!!!");
		return service.removeBusRoute(routeId);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@GetMapping("/get-busroute-by-origin-destination/{origin}/{destination}")
	public List<BusRoute> getBusRouteByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
		logger.info("Get Bus Route by origin and destination!!!!");
		return service.searchBusRoutes(origin, destination);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@PostMapping("/add-bus-schedule")
	public BusSchedule addBusSchedule(@RequestBody @Valid BusScheduleDTO busScheduleDto) {
		logger.info("Bus Schedule details added successfully!!!!");
		return service.addBusSchedule(busScheduleDto);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@PutMapping("/edit-bus-schedule/{scheduleId}")
	public BusSchedule updateBusSchedule(@RequestBody @Valid BusScheduleDTO busScheduleDto, @PathVariable int scheduleId) throws ScheduleNotFoundException {
		logger.warn("Bus Schedule details updated!!!!");
		return service.editBusSchedule(busScheduleDto, scheduleId);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@DeleteMapping("/delete-bus-schedule-by-id/{scheduleId}")
	public String deleteBusScheduleById(@PathVariable int scheduleId) {
		logger.warn("Bus Schedule details deleted successfully!!!!");
		return service.removeBusSchedule(scheduleId);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@GetMapping("/get-bookings-by-scheduleId/{scheduleId}")
	public List<Booking> getBookingByScheduleId(@PathVariable int scheduleId) {
		logger.info("Get Bookings by Schedule Id!!!!");
		return service.viewBookedTickets(scheduleId);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@GetMapping("/get-bookings-by-userId/{userId}")
	public List<Booking> getBookingByUserId(@PathVariable int userId) {
		logger.info("Get Bookings by User Id!!!!");
		return service.getBookingHistory(userId);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@GetMapping("/check-if-seat-available/{scheduleId}")
	public boolean manageSeatAvailability(@PathVariable int scheduleId) throws SeatUnavailableException {
		logger.info("Check seat availability status!!!!");
		return service.manageSeatAvailability(scheduleId);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@PutMapping("/refund-cancelled-tickets/{bookingID}")
	public boolean refundCancelledTickets(@PathVariable int bookingID) throws BookingNotFoundException {
		logger.info("Refund cancellation of tickets status!!!!");
		return service.refundCancelledTickets(bookingID);
	}
	
	@PreAuthorize("hasRole('ROLE_BUS_OPERATOR')")
	@PutMapping("/update-bus-fare-for-a-schedule/{fare}/{scheduleId}")
	public boolean updateFaresForSchedule(@PathVariable BigDecimal fare, @PathVariable int scheduleId) throws ScheduleNotFoundException{
		logger.warn("Bus fare updated!!!!");
		return service.updateFares(scheduleId, fare);
	}
}
