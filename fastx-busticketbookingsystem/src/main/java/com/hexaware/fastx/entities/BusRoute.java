package com.hexaware.fastx.entities;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Bus_Route")
public class BusRoute {
	@Id
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
    
    //One to many relationship from BusRoute to BusSchedule 
    @OneToMany(mappedBy = "busRoute")
    private Set<BusSchedule> schedules = new HashSet<>();
    
    
    
    // Enum for BusType
    public enum BusType {
        SLEEPER_WITH_AC, SLEEPER_WITHOUT_AC, SEAT_WITH_AC, SEAT_WITHOUT_AC
    }

    // Enum for Amenities
    public enum Amenities {
        WATER_BOTTLE, CHARGING_POINT, TV, BLANKET
    }

    //Default Constructor
	public BusRoute() {
		super();
	}

	//Parameterized Constructor
	public BusRoute(int routeID, String busNumber, BusType busType, String origin, String destination, Time timings,
			BigDecimal fare, Set<Amenities> amenities, Set<BusSchedule> schedules) {
		super();
		this.routeID = routeID;
		this.busNumber = busNumber;
		this.busType = busType;
		this.origin = origin;
		this.destination = destination;
		this.timings = timings;
		this.fare = fare;
		this.amenities = amenities;
		this.schedules = schedules;
	}
	
    //FOr one to many relationship between busRoute to BusSchedule 
	
    public Set<BusSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<BusSchedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(BusSchedule schedule) {
        schedules.add(schedule);
        schedule.setBusRoute(this);
    }

    public void removeSchedule(BusSchedule schedule) {
        schedules.remove(schedule);
        schedule.setBusRoute(null);
    }
	
    //FOr one to many relationship between busRoute to  BusSchedule


	//Getters and Setters Start
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
	//Getters and Setters End

	
	//ToString Method
	@Override
	public String toString() {
		return "BusRoute [routeID=" + routeID + ", busNumber=" + busNumber + ", busType=" + busType + ", origin="
				+ origin + ", destination=" + destination + ", timings=" + timings + ", fare=" + fare + ", amenities="
				+ amenities + "]";
	}
	
}
