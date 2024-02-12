package com.hexaware.fastx.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusOperatorRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AdminServiceImp implements IAdminService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BusOperatorRepository busOperatorRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	BusRouteRepository busRouteRepo;
	
	@Override
	public String deleteUserAccount(int userId) {
		userRepo.deleteById(userId);
		
		return "User deleted";
	}

	@Override
	public String deleteBusOperatorAccount(int operatorId) {
		busOperatorRepo.deleteById(operatorId);
		
		return "Bus Operator deleted";
	}

	@Override
	public List<Booking> manageBookedTickets(int userId) {
		return bookingRepo.findByUserId(userId);
	}

	@Override
	public List<BusRoute> viewAllBusRoutes() {
		return busRouteRepo.findAll();
	}

	@Override
	public BusRoute addBusRoute(BusRouteDTO busRouteDto) {
	    BusRoute busRoute = new BusRoute();
	    
	    busRoute.setOrigin(busRouteDto.getOrigin());
	    busRoute.setDestination(busRouteDto.getDestination());
	    busRoute.setDistanceCovered(busRouteDto.getDistanceCovered());
	    busRoute.setEstimatedDuration(busRouteDto.getEstimatedDuration());
	    busRoute.setRouteDescription(busRouteDto.getRouteDescription());
	    
	    return busRouteRepo.save(busRoute);
	}
	
	@Override
	public BusRoute editBusRoute(BusRouteDTO busRouteDto) {
	    Integer routeId = busRouteDto.getRouteId();
	    if (routeId != null) {
	        Optional<BusRoute> optionalBusRoute = busRouteRepo.findById(routeId);
	        if (optionalBusRoute.isPresent()) {
	            BusRoute busRoute = optionalBusRoute.get();
	            busRoute.setOrigin(busRouteDto.getOrigin());
	            busRoute.setDestination(busRouteDto.getDestination());
	            busRoute.setDistanceCovered(busRouteDto.getDistanceCovered());
	            busRoute.setEstimatedDuration(busRouteDto.getEstimatedDuration());
	            busRoute.setRouteDescription(busRouteDto.getRouteDescription());
	            return busRouteRepo.save(busRoute);
	        } else {
	            // Handle case where bus route with given ID is not found
	            throw new EntityNotFoundException("Bus route with ID " + routeId + " not found");
	        }
	    } else {
	        // Handle case where routeId is null
	        throw new IllegalArgumentException("Route ID cannot be null");
	    }
	}



	@Override
	public String removeBusRoute(int routeId) {
		busRouteRepo.deleteById(routeId);
		
		return "Bus Route deleted";
	}

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination) {
		return busRouteRepo.getBusRoutesByOriginAndDestination(origin, destination);
	}

	@Override
	public User getUserById(int userID) {
		return userRepo.findById(userID).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public BusOperator getBusOperatorById(int operatorId) {
		return busOperatorRepo.findById(operatorId).orElse(null);
	}

	@Override
	public List<BusOperator> getAllBusOperators() {
		return busOperatorRepo.findAll();
	}

}
