package com.hexaware.fastx.service;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;

public class AdminServiceImp implements IAdminService {

	@Override
	public String deleteUserAccount(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBusOperatorAccount(int operatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> manageBookedTickets(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusRoute> viewAllBusRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

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
	public BusRoute removeBusRoute(int routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusRoute> searchBusRoutes(String origin, String destination, LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusOperator getBusOperatorById(int operatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusOperator> getAllBusOperators() {
		// TODO Auto-generated method stub
		return null;
	}

}
