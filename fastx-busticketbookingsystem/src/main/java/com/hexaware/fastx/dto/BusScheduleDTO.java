package com.hexaware.fastx.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import com.hexaware.fastx.entities.BusSchedule.Amenities;
import com.hexaware.fastx.entities.BusSchedule.BusType;
import com.hexaware.fastx.entities.BusSchedule.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BusScheduleDTO {
	private String busNumber;
    private int availableSeats;
    private Date date;
    private Time timings;
    private BigDecimal fare;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Set<Amenities> amenities;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    private int operatorId;
    private int routeId;
    
	public BusScheduleDTO() {
		super();
	}

	public BusScheduleDTO(String busNumber, int availableSeats, Date date, Time timings,
			BigDecimal fare, Status status, Set<Amenities> amenities, BusType busType, int operatorId, int routeId) {
		super();
		this.busNumber = busNumber;
		this.availableSeats = availableSeats;
		this.date = date;
		this.timings = timings;
		this.fare = fare;
		this.status = status;
		this.amenities = amenities;
		this.busType = busType;
		this.operatorId = operatorId;
		this.routeId = routeId;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTimings() {
		return timings;
	}

	public void setTimings(Time timings) {
		this.timings = timings;
	}

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Amenities> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<Amenities> amenities) {
		this.amenities = amenities;
	}

	public BusType getBusType() {
		return busType;
	}

	public void setBusType(BusType busType) {
		this.busType = busType;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
}
