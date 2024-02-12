package com.hexaware.fastx.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public User registerUser(UserDTO userDto) {
		User user = new User();
	    // If the admin ID provided is invalid, set it to 1
		if (userDto.getAdminId() <= 0) {
	        userDto.setAdminId(1); // Set the admin ID to 1
		}

	    Admin admin = adminRepository.findById(userDto.getAdminId())
	                                 .orElseThrow(() -> new EntityNotFoundException("Admin not found with ID: " + userDto.getAdminId()));
		
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
	public List<BusSchedule> getAvailableSchedules(int routeId) {
		return busScheduleRepo.findByRouteId(routeId);
	}

//	@Override
//	public Map<Integer, Set<Amenities>> getFaresAndAmenities(int scheduleId) {
//		List<Object[]> results = busScheduleRepo.findScheduleIdAndAmenitiesByScheduleId(scheduleId);
//		
//		Map<Integer, Set<Amenities>> faresAndAmenities = new HashMap<>();
//		for(Object[] result : results) {
//			Integer id = (Integer) result[0];
//			Set<Amenities> amenities = new HashSet<>();
//			for(Object obj : (Object[]) result[1]) {
//				amenities.add(Amenities.valueOf((String) obj));
//			}
//			faresAndAmenities.put(id, amenities);
//		}
//		
//		return faresAndAmenities;
//	}
	
	
	@Override
	public Map<Integer, Set<Amenities>> getFaresAndAmenities(int scheduleId) {
	    List<Object[]> results = busScheduleRepo.findScheduleIdAndAmenitiesByScheduleId(scheduleId);
	    
	    Map<Integer, Set<Amenities>> faresAndAmenities = new HashMap<>();
	    for(Object[] result : results) {
	        Integer id = (Integer) result[0];
	        Set<Amenities> amenities = new HashSet<>();
	        
	        if (result[1] instanceof Object[]) {
	            for(Object obj : (Object[]) result[1]) {
	                amenities.add(Amenities.valueOf((String) obj));
	            }
	        }
	        
	        faresAndAmenities.put(id, amenities);
	    }
	    
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
		
		return bookingRepo.save(booking);
	}

	@Override 
	public List<Booking> getBookingHistory(int userId) {
		return bookingRepo.findByUserId(userId);
	}

	@Override
	public String cancelBooking(int bookingId) {
		bookingRepo.updateRefundStatus(RefundStatus.PENDING, bookingId);
		
		return "Booking cancelled";
	}

	@Override
	public User updateUserProfile(UserDTO userDto, int userId) throws UserNotFoundException {
		Admin admin = adminRepository.findById(userDto.getAdminId())
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with ID: " + userDto.getAdminId()));
		
		Optional<User> existUser = userRepo.findById(userId);
		if(existUser.isPresent()) {
			User user = new User();
			
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
		} else {
			throw new UserNotFoundException("User with given ID not found in db!");
		}
	}

	@Override
	public boolean changePassword(int userId, String newPassword) throws UserNotFoundException {
		boolean flag = false;
		
		Optional<User> existUser = userRepo.findById(userId);
		if(existUser.isPresent())
		{
			int count = userRepo.updatePassword(newPassword, userId);
			if(count > 0) {
				flag = true;
			}
		} else {
			throw new UserNotFoundException("User with the given ID not found in DB.");
		}
		
		return flag;
	}
	
//	@Override
//	public boolean changePassword(int userId, String newPassword) throws UserNotFoundException {
//	    Optional<User> existingUser = userRepo.findById(userId);
//	    if (existingUser.isPresent()) {
//	        User user = existingUser.get();
//	        user.setPassword(newPassword);
//	        userRepo.save(user);
//	        return true;
//	    } else {
//	        throw new UserNotFoundException("User with the given ID not found in DB.");
//	    }
//	}


}