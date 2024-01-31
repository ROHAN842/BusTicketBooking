package com.hexaware.fastx.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;

public class BusOperatorServiceImp implements IBusOperatorService {

	@Override
	public BusRoute addBusRoute(BusRoute busRoute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusRoute editBusRoute(BusRoute busRoute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeBusRoute(int routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusSchedule addBusSchedule(BusSchedule busSchedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusSchedule editBusSchedule(BusSchedule busSchedule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeBusSchedule(int scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusSchedule> getAvailableSchedules(int routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setFares(int routeId, BigDecimal fare) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean manageSeatAvailability(int scheduleId, int availableSeats) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Booking> viewBookedTickets(int scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean refundCancelledTickets(int scheduleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Booking> getBookingHistory(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
