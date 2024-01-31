package com.hexaware.fastx.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;

public interface IAdminService {
    // Deletes a user account
    String deleteUserAccount(int userId);

    // Deletes a bus operator account
    String deleteBusOperatorAccount(int operatorId);

    // Manages booked tickets by the user
    List<Booking> manageBookedTickets(int userId);

    // Views all bus routes
    List<BusRoute> viewAllBusRoutes();
    
 // Adds a new bus route
    BusRoute addBusRoute(BusRoute busRoute);

    // Edits an existing bus route
    BusRoute editBusRoute(BusRoute busRoute);

    // Removes a bus route
    BusRoute removeBusRoute(int routeId);
    
    List<BusRoute> searchBusRoutes(String origin, String destination, LocalDate date);
    
    User getUserById(int userID);
    
    List<User> getAllUsers();
    
    BusOperator getBusOperatorById(int operatorId);
    
    List<BusOperator> getAllBusOperators();
}

