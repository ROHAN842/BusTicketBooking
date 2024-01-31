package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.User;

public class UserServiceImp implements IUserService {

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User loginUser(String usernameOrEmail, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAutoSuggestions(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusSchedule> getAvailableSchedules(int routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getFaresAndAmenities(int routeId, int numberOfSeats) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean selectSeats(List<String> selectedSeats) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BigDecimal calculateTotalPrice(int routeId, List<String> selectedSeats) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking bookTickets(int userId, int routeId, List<String> selectedSeats) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getBookingHistory(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserProfile(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(int userId, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
