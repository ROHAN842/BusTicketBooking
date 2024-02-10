package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusOperatorRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.BusScheduleRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class BusOperatorServiceImp implements IBusOperatorService {

	@Autowired
	BusRouteRepository busRouteRepo;
	
	@Autowired
	BusScheduleRepository busScheduleRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	BusOperatorRepository busOperatorRepo;
	
	@Override
	public BusOperator registerBusOperator(BusOperatorDTO busOperatorDto) {
		BusOperator busOperator = new BusOperator();
		Admin admin = new Admin();
		admin.setAdminId(1);
		
		busOperator.setOperatorUsername(busOperatorDto.getOperatorUsername());
		busOperator.setOperatorPassword(busOperatorDto.getOperatorPassword());
		busOperator.setoperatorName(busOperatorDto.getOperatorname());
		busOperator.setEmailId(busOperatorDto.getEmailId());
		busOperator.setPhoneNumber(busOperatorDto.getPhoneNumber());
		busOperator.setRegistrationDate(busOperatorDto.getRegistrationDate());
		busOperator.setAdmin(admin);
		
		return busOperatorRepo.save(busOperator);
	}
	
	@Override
	public String loginBusOperator() {
		return "";
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
			
			return busRouteRepo.save(busRoute);
		} else {
			throw new BusRouteNotFoundException("Bus Routes with given ID not found in DB.");
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
	public BusSchedule addBusSchedule(BusScheduleDTO busScheduleDto) {
		BusSchedule busSchedule = new BusSchedule();
		
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
			
			
			return busScheduleRepo.save(busSchedule);
		} else {
			throw new ScheduleNotFoundException("Schedule with the given ID not found in DB.");
		}
		
	}

	@Override
	public String removeBusSchedule(int scheduleId) {
		busScheduleRepo.deleteById(scheduleId);
		
		return "Bus Schedule removed";
	}

	@Override
	public List<BusSchedule> getAvailableSchedules(int routeId) {
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
		} else {
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
		} else
			throw new SeatUnavailableException("Seats ran out!");
		
		return flag;
	}

	@Override
	public List<Booking> viewBookedTickets(int scheduleId) {
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
			}
		}
		else {
			throw new BookingNotFoundException("Booking with the given ID not found in DB.");
		}
		
		return flag;
	}

	@Override
	public List<Booking> getBookingHistory(int userId) {
		return bookingRepo.findByUserId(userId);
	}

}