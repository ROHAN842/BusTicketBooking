package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;

public interface IBusOperatorService {
    // Adds a new bus route
    BusRoute addBusRoute(BusRoute busRoute);

    // Edits an existing bus route
    BusRoute editBusRoute(BusRoute busRoute);

    // Removes a bus route
    String removeBusRoute(int routeId);
    
    List<BusRoute> searchBusRoutes(String origin, String destination, LocalDate date);


    // Adds a new bus schedule
    BusSchedule addBusSchedule(BusSchedule busSchedule);

    // Edits an existing bus schedule
    BusSchedule editBusSchedule(BusSchedule busSchedule);

    // Removes a bus schedule
    String removeBusSchedule(int scheduleId);
    
    List<BusSchedule> getAvailableSchedules(int routeId);


    // Sets fares for a specific route
    boolean setFares(int routeId, BigDecimal fare);

    // Manages seat availability for a specific schedule
    boolean manageSeatAvailability(int scheduleId, int availableSeats);

    // Views the tickets booked for a specific bus schedule
    List<Booking> viewBookedTickets(int scheduleId);

    // Refunds the amount for cancelled tickets on a specific bus schedule
    boolean refundCancelledTickets(int scheduleId);

    List<Booking> getBookingHistory(int userId);

}

