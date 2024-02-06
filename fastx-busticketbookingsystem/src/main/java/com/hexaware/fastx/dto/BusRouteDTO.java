package com.hexaware.fastx.dto;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Set;

import com.hexaware.fastx.entities.BusRoute.Amenities;
import com.hexaware.fastx.entities.BusRoute.BusType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BusRouteDTO {
	private int routeID;
    private String busNumber;
    @Enumerated(EnumType.STRING)
    private BusType busType;
    private String origin;
    private String destination;
    private Time timings;
    private BigDecimal fare;
    @Enumerated(EnumType.STRING)
    private Set<Amenities> amenities;
	public BusRouteDTO() {
		super();
	}
	public BusRouteDTO(int routeID, String busNumber, BusType busType, String origin, String destination, Time timings,
			BigDecimal fare, Set<Amenities> amenities) {
		super();
		this.routeID = routeID;
		this.busNumber = busNumber;
		this.busType = busType;
		this.origin = origin;
		this.destination = destination;
		this.timings = timings;
		this.fare = fare;
		this.amenities = amenities;
	}
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public BusType getBusType() {
		return busType;
	}
	public void setBusType(BusType busType) {
		this.busType = busType;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
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
	public Set<Amenities> getAmenities() {
		return amenities;
	}
	public void setAmenities(Set<Amenities> amenities) {
		this.amenities = amenities;
	}
    
}
