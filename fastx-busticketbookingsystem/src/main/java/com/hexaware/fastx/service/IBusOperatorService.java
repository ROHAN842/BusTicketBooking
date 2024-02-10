package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.util.List;

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

public interface IBusOperatorService {
	
	// Register Bus Operator
	BusOperator registerBusOperator(BusOperatorDTO busOperatorDto);
	
	//Login Bus Operator
	String loginBusOperator();
	
    // Adds a new bus route
    BusRoute addBusRoute(BusRouteDTO busRouteDto);

    // Edits an existing bus route
    BusRoute editBusRoute(BusRouteDTO busRouteDto, int routeId) throws BusRouteNotFoundException;

    // Removes a bus route
    String removeBusRoute(int routeId);
    
    List<BusRoute> searchBusRoutes(String origin, String destination);


    // Adds a new bus schedule
    BusSchedule addBusSchedule(BusScheduleDTO busScheduleDto);

    // Edits an existing bus schedule
    BusSchedule editBusSchedule(BusScheduleDTO busScheduleDto, int scheduleId) throws ScheduleNotFoundException;

    // Removes a bus schedule
    String removeBusSchedule(int scheduleId);
    
    List<BusSchedule> getAvailableSchedules(int routeId);


    // Sets fares for a specific route
    boolean updateFares(int scheduleId, BigDecimal fare)  throws ScheduleNotFoundException;

    // Manages seat availability for a specific schedule
    boolean manageSeatAvailability(int scheduleId) throws SeatUnavailableException;

    // Views the tickets booked for a specific bus schedule
    List<Booking> viewBookedTickets(int scheduleId);

    // Refunds the amount for cancelled tickets on a specific bus schedule
    boolean refundCancelledTickets(int bookingID) throws BookingNotFoundException;

    List<Booking> getBookingHistory(int userId);

}