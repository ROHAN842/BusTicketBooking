package com.hexaware.fastx.service;

import java.util.List;

import com.hexaware.fastx.dto.AuthenticationRequest;
import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.BusRouteNotFoundException;

public interface IAdminService {
	
	String loginAdmin(AuthenticationRequest authenticationRequest);
	
    // Deletes a user account
    String deleteUserAccount(int userId);

    // Deletes a bus operator account
    String deleteBusOperatorAccount(int operatorId);

    // Manages booked tickets by the user
    List<Booking> manageBookedTickets(int userId);

    // Views all bus routes
    List<BusRoute> viewAllBusRoutes();
    
 // Adds a new bus route
    BusRoute addBusRoute(BusRouteDTO busRouteDto);

    // Edits an existing bus route
    BusRoute editBusRoute(BusRouteDTO busRouteDto, int routeId) throws BusRouteNotFoundException;

    // Removes a bus route
    String removeBusRoute(int routeId);
    
    List<BusRoute> searchBusRoutes(String origin, String destination);
    
    User getUserById(int userID);
    
    List<User> getAllUsers();
    
    BusOperator getBusOperatorById(int operatorId);
    
    List<BusOperator> getAllBusOperators();
}