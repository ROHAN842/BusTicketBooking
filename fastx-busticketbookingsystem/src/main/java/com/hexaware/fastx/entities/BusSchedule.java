package com.hexaware.fastx.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Bus_Schedule")

public class BusSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleID;
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

    // Collection of Booking entities, one to many relationship from busSchedule to booking 
    @OneToMany(mappedBy = "busSchedule", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();
    
    // Many to one relationship from busSchdule to BusRoute 
    @ManyToOne
    @JoinColumn(name = "routeID")
    private BusRoute busRoute;
    
    // Many to one relationship from busSchdule to BusOperator 
    @ManyToOne
    @JoinColumn(name = "busoperatorID")
    private BusOperator operator;
    
    // Enum for Status
    public enum Status {
        ACTIVE, INACTIVE
    }
    
    // Enum for BusType
    public enum BusType {
        SLEEPER_WITH_AC, SLEEPER_WITHOUT_AC, SEAT_WITH_AC, SEAT_WITHOUT_AC
    }

    // Enum for Amenities
    public enum Amenities {
        WATER_BOTTLE, CHARGING_POINT, TV, BLANKET
    }

    //Default Constructor
	public BusSchedule() {
		super();
	}
	
	public BusSchedule(int scheduleID, String busNumber, int availableSeats, Date date, Time timings, BigDecimal fare,
			Status status, Set<Amenities> amenities, BusType busType, Set<Booking> bookings) {
		super();
		this.scheduleID = scheduleID;
		this.busNumber = busNumber;
		this.availableSeats = availableSeats;
		this.date = date;
		this.timings = timings;
		this.fare = fare;
		this.status = status;
		this.amenities = amenities;
		this.busType = busType;
		this.bookings = bookings;
	}

	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
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

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public BusRoute getBusRoute() {
		return busRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}

	public BusOperator getOperator() {
		return operator;
	}

	public void setOperator(BusOperator operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "BusSchedule [scheduleID=" + scheduleID + ", busNumber=" + busNumber + ", availableSeats="
				+ availableSeats + ", date=" + date + ", timings=" + timings + ", fare=" + fare + ", status=" + status
				+ ", amenities=" + amenities + ", busType=" + busType + ", bookings=" + bookings + ", busRoute="
				+ busRoute + ", operator=" + operator + "]";
	}

}