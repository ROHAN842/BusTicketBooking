package com.hexaware.fastx.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.BusSchedule.Amenities;
import com.hexaware.fastx.exception.UserNotFoundException;
import com.hexaware.fastx.entities.User;

public interface IUserService {

    User registerUser(UserDTO userDto);

    User loginUser(String usernameOrEmail, String password);

    List<BusRoute> searchBusRoutes(String origin, String destination);

    List<BusSchedule> getAvailableSchedules(int routeId);

    Map<Integer, Set<Amenities>> getFaresAndAmenities(int scheduleId);

    Booking bookTickets(BookingDTO bookingDtos);

    List<Booking> getBookingHistory(int userId);

    String cancelBooking(int bookingId);

    User updateUserProfile(UserDTO userDto);

    boolean changePassword(int userId, String newPassword) throws UserNotFoundException;




}