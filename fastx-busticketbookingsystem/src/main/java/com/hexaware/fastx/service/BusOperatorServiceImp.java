package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
import com.hexaware.fastx.dto.BusOperatorDTO;
import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.dto.BusScheduleDTO;
import com.hexaware.fastx.entities.Admin;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.Booking.RefundStatus;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.exception.BookingNotFoundException;
import com.hexaware.fastx.exception.BusRouteNotFoundException;
import com.hexaware.fastx.exception.ScheduleNotFoundException;
import com.hexaware.fastx.exception.SeatUnavailableException;
import com.hexaware.fastx.repository.AdminRepository;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusOperatorRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.BusScheduleRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class BusOperatorServiceImp implements IBusOperatorService {

	private final BusRouteRepository busRouteRepo;
    private final BusScheduleRepository busScheduleRepo;
    private final BookingRepository bookingRepo;
    private final BusOperatorRepository busOperatorRepo;
    private final AdminRepository adminRepository;

    @Autowired
    public BusOperatorServiceImp(BusRouteRepository busRouteRepo, 
                         BusScheduleRepository busScheduleRepo, 
                         BookingRepository bookingRepo, 
                         BusOperatorRepository busOperatorRepo, 
                         AdminRepository adminRepository) {
        this.busRouteRepo = busRouteRepo;
        this.busScheduleRepo = busScheduleRepo;
        this.bookingRepo = bookingRepo;
        this.busOperatorRepo = busOperatorRepo;
        this.adminRepository = adminRepository;
    }
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
		AuthenticationManager authenticationManager;
		
		@Autowired
		JwtService jwtService;
	
		Logger logger = LoggerFactory.getLogger(BusOperatorServiceImp.class);
	@Override
	public BusOperator registerBusOperator(BusOperatorDTO busOperatorDto) {
		BusOperator busOperator = new BusOperator();
		Admin admin = adminRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with ID"));
		
		busOperator.setOperatorUsername(busOperatorDto.getOperatorUsername());
		busOperator.setOperatorPassword(passwordEncoder.encode(busOperatorDto.getOperatorPassword()));
		busOperator.setoperatorName(busOperatorDto.getOperatorName());
		busOperator.setEmailId(busOperatorDto.getEmailId());
		busOperator.setPhoneNumber(busOperatorDto.getPhoneNumber());
		busOperator.setRegistrationDate(busOperatorDto.getRegistrationDate());
		busOperator.setRoles(busOperatorDto.getRoles());
		busOperator.setAdmin(admin);
		
		logger.info("Bus operator registered!");
		
		return busOperatorRepo.save(busOperator);
	}
	
	@Override
	public BusRoute addBusRoute(BusRouteDTO busRouteDto) {
		BusRoute busRoute = new BusRoute();
		
		busRoute.setOrigin(busRouteDto.getOrigin());
		busRoute.setDestination(busRouteDto.getDestination());
		busRoute.setDistanceCovered(busRouteDto.getDistanceCovered());
		busRoute.setEstimatedDuration(busRouteDto.getEstimatedDuration());
		busRoute.setRouteDescription(busRouteDto.getRouteDescription());
		
		logger.info("Bus Route added.");
		
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
		busRouteRepo.deleteById(routeId);
		
		logger.warn("Bus route deleted");
		
		return "Bus Route deleted";
	}

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination) {
		logger.info("Listing bus routes by origin and destination.");
		
		return busRouteRepo.getBusRoutesByOriginAndDestination(origin, destination);
	}

	@Override
	public BusSchedule addBusSchedule(BusScheduleDTO busScheduleDto) {
		BusSchedule busSchedule = new BusSchedule();
		
		BusRoute busRoute = busRouteRepo.findById(busScheduleDto.getRouteId())
				.orElseThrow(() -> new EntityNotFoundException("Route not found!"));
		
		BusOperator busOperator = busOperatorRepo.findById(busScheduleDto.getOperatorId())
				.orElseThrow(() -> new EntityNotFoundException("Operator not found!"));
		
		busSchedule.setBusNumber(busScheduleDto.getBusNumber());
		busSchedule.setAvailableSeats(busScheduleDto.getAvailableSeats());
		busSchedule.setDate(busScheduleDto.getDate());
		busSchedule.setTimings(busScheduleDto.getTimings());
		busSchedule.setStatus(busScheduleDto.getStatus());
		busSchedule.setAmenities(busScheduleDto.getAmenities());
		busSchedule.setBusType(busScheduleDto.getBusType());
		busSchedule.setBusRoute(busRoute);
		busSchedule.setOperator(busOperator);
		
		logger.info("Bus schedule added!");
		
		return busScheduleRepo.save(busSchedule);
	}

	@Override
	public BusSchedule editBusSchedule(BusScheduleDTO busScheduleDto, int scheduleId) throws ScheduleNotFoundException {
		BusSchedule busSchedule = null;
		Optional<BusSchedule> existSchedule = busScheduleRepo.findById(scheduleId);
		
		if(existSchedule.isPresent()) {
			busSchedule = existSchedule.get();
			
			BusRoute busRoute = new BusRoute();
			busRoute.setRouteID(busScheduleDto.getRouteId());
			
			BusOperator busOperator = new BusOperator();
			busOperator.setOperatorId(busScheduleDto.getOperatorId());
			
			busSchedule.setBusNumber(busScheduleDto.getBusNumber());
			busSchedule.setAvailableSeats(busScheduleDto.getAvailableSeats());
			busSchedule.setDate(busScheduleDto.getDate());
			busSchedule.setTimings(busScheduleDto.getTimings());
			busSchedule.setStatus(busScheduleDto.getStatus());
			busSchedule.setAmenities(busScheduleDto.getAmenities());
			busSchedule.setBusType(busScheduleDto.getBusType());
			busSchedule.setBusRoute(busRoute);
			busSchedule.setOperator(busOperator);
			
			logger.warn("Bus Schedule details updated.");
			
			return busScheduleRepo.save(busSchedule);
		} else {
			logger.error("Bus schedule with given ID not found.");
			throw new ScheduleNotFoundException("Schedule with the given ID not found in DB.");
		}
		
	}

	@Override
	public String removeBusSchedule(int scheduleId) {
		busScheduleRepo.deleteById(scheduleId);
		
		logger.warn("Bus schedule deleted!");
		
		return "Bus Schedule removed";
	}

	@Override
	public List<BusSchedule> getAvailableSchedules(int routeId) {
		logger.info("Listing all available bus schedules for a route ID.");
		return busScheduleRepo.findByRouteId(routeId);
	}

	@Override
	public boolean updateFares(int scheduleId, BigDecimal fare) throws ScheduleNotFoundException {
		boolean flag = false;
		Optional<BusSchedule> existSchedule = busScheduleRepo.findById(scheduleId);
		if(existSchedule.isPresent())
		{
			int count = busScheduleRepo.updateFare(fare, scheduleId);
			if(count > 0)
				flag = true;
			logger.warn("Updating fares for bus schedule.");
		} else {
			logger.error("Bus schedule with given ID not found!");
			throw new ScheduleNotFoundException("Bus schedule with the given ID not found.");
		}
		
		return flag;
	}

	@Override
	public boolean manageSeatAvailability(int scheduleId) throws SeatUnavailableException {
		boolean flag = false;
		Optional<BusSchedule> existSchedule = busScheduleRepo.findById(scheduleId);
		
		if(existSchedule.isPresent()) {
			BusSchedule schedule = existSchedule.get();
			if(schedule.getAvailableSeats() > 0)
				flag = true;
			logger.info("Getting seat availability!");
		} else
		{
			logger.error("Seats not available.");
			throw new SeatUnavailableException("Seats ran out!");
		}
		
		return flag;
	}

	@Override
	public List<Booking> viewBookedTickets(int scheduleId) {
		logger.info("Viewing booked tickets!");
		return bookingRepo.findByScheduleId(scheduleId);
	}

	@Override
	public boolean refundCancelledTickets(int bookingID) throws BookingNotFoundException {
		boolean flag = false;
		
		Optional<Booking> existBooking = bookingRepo.findById(bookingID);
		
		if(existBooking.isPresent())
		{
			Booking booking = existBooking.get();
			if(booking.getRefundStatus() == RefundStatus.PENDING) {
				int count = bookingRepo.updateRefundStatus(RefundStatus.PROCESSED, bookingID);
				if(count > 0)
				{
					flag = true;
				}
				logger.warn("Cancelling tickets. Setting refund status to PROCESSED from PENDING");
			}
		}
		else {
			logger.error("Booking ID not found!");
			throw new BookingNotFoundException("Booking with the given ID not found in DB.");
		}
		
		return flag;
	}

	@Override
	public List<Booking> getBookingHistory(int userId) {
		logger.info("Listing booking history of a user ID");
		return bookingRepo.findByUserId(userId);
	}

	@Override
	public String loginBusOperator(AuthenticationRequest authenticationRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		String token = null;
		
		if(authentication.isAuthenticated()) {
			
			// call generate token method from jwtService class
			logger.info("Generating token for Bus operator");
			token =	jwtService.generateToken(authenticationRequest.getUsername());
		}
		else {
			logger.error("Username or password for bus operator is incorrect!");
			 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");
			
		}

		return token;
	}

}