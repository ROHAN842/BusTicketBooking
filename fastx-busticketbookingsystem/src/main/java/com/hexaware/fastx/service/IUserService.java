package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.User;

public interface IUserService {

    User registerUser(UserDTO userDto);

    User loginUser(String usernameOrEmail, String password);

    List<BusRoute> searchBusRoutes(String origin, String destination);

    List<String> getAutoSuggestions(String input);

    List<BusSchedule> getAvailableSchedules(int routeId);

    Map<String, Object> getFaresAndAmenities(int routeId, int numberOfSeats);

    boolean selectSeats(List<String> selectedSeats);

    BigDecimal calculateTotalPrice(int routeId, List<String> selectedSeats);

    Booking bookTickets(int userId, int routeId, List<String> selectedSeats);

    List<Booking> getBookingHistory(int userId);

    String cancelBooking(int bookingId);

    User updateUserProfile(UserDTO userDto);

    boolean changePassword(int userId, String newPassword);




}