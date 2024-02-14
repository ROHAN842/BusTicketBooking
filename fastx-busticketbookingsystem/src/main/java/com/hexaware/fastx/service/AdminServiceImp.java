package com.hexaware.fastx.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.AuthenticationRequest;
import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.BusRouteNotFoundException;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusOperatorRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.UserRepository;

@Service
public class AdminServiceImp implements IAdminService {

	private final UserRepository userRepo;
    private final BusOperatorRepository busOperatorRepo;
    private final BookingRepository bookingRepo;
    private final BusRouteRepository busRouteRepo;

    @Autowired
    public AdminServiceImp(UserRepository userRepo, 
                         BusOperatorRepository busOperatorRepo, 
                         BookingRepository bookingRepo, 
                         BusRouteRepository busRouteRepo) {
        this.userRepo = userRepo;
        this.busOperatorRepo = busOperatorRepo;
        this.bookingRepo = bookingRepo;
        this.busRouteRepo = busRouteRepo;
    }
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	Logger logger = LoggerFactory.getLogger(AdminServiceImp.class);
	
	@Override
	public String deleteUserAccount(int userId) {
		
		logger.warn("User deleted!");
		
		userRepo.deleteById(userId);
		
		return "User deleted";
	}

	@Override
	public String deleteBusOperatorAccount(int operatorId) {
		logger.warn("Bus Operator deleted");
		
		busOperatorRepo.deleteById(operatorId);
		
		return "Bus Operator deleted";
	}

	@Override
	public List<Booking> manageBookedTickets(int userId) {
		logger.info("Listing booking history of the user");
		
		return bookingRepo.findByUserId(userId);
	}

	@Override
	public List<BusRoute> viewAllBusRoutes() {
		logger.info("Listing all bus routes.");
		
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
		
		logger.info("Adding bus route.");
		
		return busRouteRepo.save(busRoute);
	}

	@Override
	public BusRoute editBusRoute(BusRouteDTO busRouteDto, int routeId) throws BusRouteNotFoundException {
		Optional<BusRoute> existRoute = busRouteRepo.findById(routeId);
		BusRoute busRoute = null;
		
		if(existRoute.isPresent()) {
			busRoute = existRoute.get();
			
			busRoute.setOrigin(busRouteDto.getOrigin());
			busRoute.setDestination(busRouteDto.getDestination());
			busRoute.setDistanceCovered(busRouteDto.getDistanceCovered());
			busRoute.setEstimatedDuration(busRouteDto.getEstimatedDuration());
			busRoute.setRouteDescription(busRouteDto.getRouteDescription());
			
			logger.warn("Bus Route details updated");
			
			return busRouteRepo.save(busRoute);
		} else {
			logger.error("Bus route with given ID not found!");
			throw new BusRouteNotFoundException("Bus Routes with given ID not found in DB.");
		}
	}

	@Override
	public String removeBusRoute(int routeId) {
		logger.warn("Bus Route deleted.");
		
		busRouteRepo.deleteById(routeId);
		
		return "Bus Route deleted";
	}

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination) {
		logger.info("Searching bus routes by origin and destination.");
		
		return busRouteRepo.getBusRoutesByOriginAndDestination(origin, destination);
	}

	@Override
	public User getUserById(int userID) {
		logger.info("Getting user details by User id.");
		
		return userRepo.findById(userID).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		logger.info("Listing all users.");
		
		return userRepo.findAll();
	}

	@Override
	public BusOperator getBusOperatorById(int operatorId) {
		logger.info("Getting bus operator details by operator ID.");
		
		return busOperatorRepo.findById(operatorId).orElse(null);
	}

	@Override
	public List<BusOperator> getAllBusOperators() {
		logger.info("Listing all bus operators!");
		
		return busOperatorRepo.findAll();
	}

	@Override
	public String loginAdmin(AuthenticationRequest authenticationRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		String token = null;
		
		if(authentication.isAuthenticated()) {
			
			// call generate token method from jwtService class
			logger.info("Generating token for admin credentials.");
			token = jwtService.generateToken(authenticationRequest.getUsername());
		}
		else {
			logger.error("Username or password credentials is incorrect.");
			 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");
			
		}

		return token;
	}

}
