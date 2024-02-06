package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.dto.BusScheduleDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.BusScheduleRepository;
<<<<<<< HEAD

=======
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
@Service
public class BusOperatorServiceImp implements IBusOperatorService {

	@Autowired
	BusRouteRepository busRouteRepo;
	
	@Autowired
	BusScheduleRepository busScheduleRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Override
	public BusRoute addBusRoute(BusRouteDTO busRouteDto) {
		BusRoute busRoute = new BusRoute();
		
		busRoute.setRouteID(busRouteDto.getRouteID());
		busRoute.setBusNumber(busRouteDto.getBusNumber());
		busRoute.setBusType(busRouteDto.getBusType());
		busRoute.setOrigin(busRouteDto.getOrigin());
		busRoute.setDestination(busRouteDto.getDestination());
		busRoute.setTimings(busRouteDto.getTimings());
		busRoute.setFare(busRouteDto.getFare());
		busRoute.setAmenities(busRouteDto.getAmenities());
		
		return busRouteRepo.save(busRoute);
	}

	@Override
	public BusRoute editBusRoute(BusRouteDTO busRouteDto) {
		BusRoute busRoute = new BusRoute();
		
		busRoute.setRouteID(busRouteDto.getRouteID());
		busRoute.setBusNumber(busRouteDto.getBusNumber());
		busRoute.setBusType(busRouteDto.getBusType());
		busRoute.setOrigin(busRouteDto.getOrigin());
		busRoute.setDestination(busRouteDto.getDestination());
		busRoute.setTimings(busRouteDto.getTimings());
		busRoute.setFare(busRouteDto.getFare());
		busRoute.setAmenities(busRouteDto.getAmenities());
		
		return busRouteRepo.save(busRoute);
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
		
		busSchedule.setScheduleID(busScheduleDto.getScheduleID());
		busSchedule.setAvailableSeats(busScheduleDto.getAvailableSeats());
		busSchedule.setDate(busScheduleDto.getDate());
		busSchedule.setStatus(busScheduleDto.getStatus());
		busSchedule.setBusRoute(busRoute);
		busSchedule.setOperator(busOperator);
		
		
		return busScheduleRepo.save(busSchedule);
	}

	@Override
	public BusSchedule editBusSchedule(BusScheduleDTO busScheduleDto) {
		BusSchedule busSchedule = new BusSchedule();
		
		BusRoute busRoute = new BusRoute();
		busRoute.setRouteID(busScheduleDto.getRouteId());
		
		BusOperator busOperator = new BusOperator();
		busOperator.setOperatorId(busScheduleDto.getOperatorId());
		
		busSchedule.setScheduleID(busScheduleDto.getScheduleID());
		busSchedule.setAvailableSeats(busScheduleDto.getAvailableSeats());
		busSchedule.setDate(busScheduleDto.getDate());
		busSchedule.setStatus(busScheduleDto.getStatus());
		busSchedule.setBusRoute(busRoute);
		busSchedule.setOperator(busOperator);
		
		
		return busScheduleRepo.save(busSchedule);
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
	public boolean setFares(int routeId, BigDecimal fare) {
		return false;
	}

	@Override
	public boolean manageSeatAvailability(int scheduleId, int availableSeats) {
		return false;
	}

	@Override
	public List<Booking> viewBookedTickets(int scheduleId) {
<<<<<<< HEAD
		return bookingRepo.findByScheduleId(scheduleId);
=======
		return bookingRepo.findByBusScheduleId(scheduleId);
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
	}

	@Override
	public boolean refundCancelledTickets(int scheduleId) {
		return false;
	}

	@Override
	public List<Booking> getBookingHistory(int userId) {
		return bookingRepo.findByUserId(userId);
	}

}