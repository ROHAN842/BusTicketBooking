package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.Admin;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.BusScheduleRepository;
import com.hexaware.fastx.repository.UserRepository;

@Service
public class UserServiceImp implements IUserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BusRouteRepository busRouteRepo;
	
	@Autowired
	BusScheduleRepository busScheduleRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Override
	public User registerUser(UserDTO userDto) {
		User user = new User();
		Admin admin = new Admin();
		admin.setAdminId(userDto.getAdminId());
		
		user.setUserID(userDto.getUserID());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setAddress(userDto.getAddress());
		user.setRegistrationDate(userDto.getRegistrationDate());
		user.setAdmin(admin);
		
		return userRepo.save(user);
	}

	@Override
	public User loginUser(String usernameOrEmail, String password) {
		return null;
	}

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination) {
		return busRouteRepo.getBusRoutesByOriginAndDestination(origin, destination);
	}

	@Override
	public List<String> getAutoSuggestions(String input) {
		return null;
	}

	@Override
	public List<BusSchedule> getAvailableSchedules(int routeId) {
		return busScheduleRepo.findByRouteId(routeId);
	}

	@Override
	public Map<String, Object> getFaresAndAmenities(int routeId, int numberOfSeats) {
		return null;
	}

	@Override
	public boolean selectSeats(List<String> selectedSeats) {
		return false;
	}

	@Override
	public BigDecimal calculateTotalPrice(int routeId, List<String> selectedSeats) {
		return null;
	}

	@Override
	public Booking bookTickets(int userId, int routeId, List<String> selectedSeats) {
		return null;
	}

	@Override
	public List<Booking> getBookingHistory(int userId) {
		return bookingRepo.findByUserId(userId);
	}

	@Override
	public String cancelBooking(int bookingId) {
		bookingRepo.deleteById(bookingId);
		
		return "Booking cancelled";
	}

	@Override
	public User updateUserProfile(UserDTO userDto) {
		User user = new User();
		Admin admin = new Admin();
		admin.setAdminId(userDto.getAdminId());
		
		user.setUserID(userDto.getUserID());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setAddress(userDto.getAddress());
		user.setRegistrationDate(userDto.getRegistrationDate());
		user.setAdmin(admin);
		
		return userRepo.save(user);
	}

	@Override
	public boolean changePassword(int userId, String newPassword) {
		return false;
	}

}
