package com.hexaware.fastx.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.AuthenticationRequest;
import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.Admin;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.Booking.RefundStatus;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.BusSchedule.Amenities;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.UserNotFoundException;
import com.hexaware.fastx.repository.AdminRepository;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.BusScheduleRepository;
import com.hexaware.fastx.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements IUserService {

	@Autowired 
	UserRepository userRepo;
	
	@Autowired
	BusRouteRepository busRouteRepo;
	
	@Autowired
	BusScheduleRepository busScheduleRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImp.class);
	
	@Override
	public User registerUser(UserDTO userDto) {
		User user = new User();
		// Load the Admin entity from the database
	    Admin admin = adminRepository.findById(1)
	                                 .orElseThrow(() -> new EntityNotFoundException("Admin not found with ID: 1"));
		
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setAddress(userDto.getAddress());
		user.setRegistrationDate(userDto.getRegistrationDate());
		user.setRoles(userDto.getRoles());
		user.setAdmin(admin);
		
		logger.info("User registered successfully.");
		
		return userRepo.save(user);
	}

	

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination) {
		logger.info("Searching bus routes by origin and destination.");
		return busRouteRepo.getBusRoutesByOriginAndDestination(origin, destination);
	}

	@Override
	public List<BusSchedule> getAvailableSchedules(int routeId) {
		logger.info("Listing available schedules by route ID.");
		return busScheduleRepo.findByRouteId(routeId);
	}

	@Override
	public Map<Integer, Set<Amenities>> getFaresAndAmenities(int scheduleId) {
		List<Object[]> results = busScheduleRepo.findScheduleIdAndAmenitiesByScheduleId(scheduleId);
		
		Map<Integer, Set<Amenities>> faresAndAmenities = new HashMap<>();
		for(Object[] result : results) {
			Integer id = (Integer) result[0];
			Set<Amenities> amenities = new HashSet<>();
			for(Object obj : (Object[]) result[1]) {
				amenities.add(Amenities.valueOf((String) obj));
			}
			faresAndAmenities.put(id, amenities);
		}
		
		logger.info("Getting fares and amenities for a particular bus schedule by ID.");
		
		return faresAndAmenities;
	}

	@Override
	public Booking bookTickets(BookingDTO bookingDto) {
		Booking booking = new Booking();
		
		BusSchedule busSchedule = busScheduleRepo.findById(bookingDto.getScheduleId())
				.orElseThrow(() -> new EntityNotFoundException("Bus Schedule not found"));
		
		User user = userRepo.findById(bookingDto.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		
		booking.setTotalNumberOfSeats(bookingDto.getTotalNumberOfSeats());
		booking.setBookingDate(bookingDto.getBookingDate());
		booking.setPaymentDate(bookingDto.getPaymentDate());
		booking.setPaymentStatus(bookingDto.getPaymentStatus());
		booking.setRefundStatus(bookingDto.getRefundStatus());
		booking.setUser(user);
		booking.setBusSchedule(busSchedule);
		
		int selectedSeats = bookingDto.getTotalNumberOfSeats();
		
		logger.info("Updating number of seats in Bus Schedule");
		busScheduleRepo.updateSeats(selectedSeats, bookingDto.getScheduleId());
		
		logger.info("Booking tickets for the trip.");
		
		return bookingRepo.save(booking);
	}

	@Override 
	public List<Booking> getBookingHistory(int userId) {
		logger.info("List booking details for a particular user.");
		return bookingRepo.findByUserId(userId);
	}

	@Override
	public String cancelBooking(int bookingId) {
		bookingRepo.updateRefundStatus(RefundStatus.PENDING, bookingId);
		
		logger.warn("Cancelling booking! Setting refund status to 'PENDING'.");
		
		return "Booking cancelled";
	}

	@Override
	public User updateUserProfile(UserDTO userDto, int userId) throws UserNotFoundException {
		Admin admin = adminRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with ID: 1"));
		
		Optional<User> existUser = userRepo.findById(userId);
		if(existUser.isPresent()) {
			User user = new User();
			
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user.setEmail(userDto.getEmail());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setPhoneNumber(userDto.getPhoneNumber());
			user.setAddress(userDto.getAddress());
			user.setRegistrationDate(userDto.getRegistrationDate());
			user.setRoles(userDto.getRoles());
			user.setAdmin(admin);
			
			logger.warn("User details updated for the given ID.");
			
			return userRepo.save(user);
		} else {
			logger.error("User with given ID not found");
			throw new UserNotFoundException("User with given ID not found in db!");
		}
	}

	@Override
	public boolean changePassword(int userId, String newPassword) throws UserNotFoundException {
		boolean flag = false;
		
		Optional<User> existUser = userRepo.findById(userId);
		if(existUser.isPresent())
		{
			passwordEncoder.encode(newPassword);
			int count = userRepo.updatePassword(newPassword, userId);
			if(count > 0) {
				flag = true;
			}
			logger.warn("Updating password for the user");
		} else {
			logger.error("User not found");
			throw new UserNotFoundException("User with the given ID not found in DB.");
		}
		
		return flag;
	}



	@Override
	public String loginUser(AuthenticationRequest authenticationRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		String token = null;
		
		if(authentication.isAuthenticated()) {
			
			// call generate token method from jwtService class
			
			token =	jwtService.generateToken(authenticationRequest.getUsername());
		}
		else {
			logger.error("Username or password entered is incorrect!");
			 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");
			
		}
		logger.info("Generating token to verify credentials of the user");
		return token;
	}

}