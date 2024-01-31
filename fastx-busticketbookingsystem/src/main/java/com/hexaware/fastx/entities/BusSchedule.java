package com.hexaware.fastx.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Bus_Schedule")

public class BusSchedule {
	@Id
    private int scheduleID;
    private int availableSeats;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    

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

    //Default Constructor
	public BusSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public BusSchedule(int scheduleID, int availableSeats, Date date, Status status, Set<Booking> bookings) {
		super();
		this.scheduleID = scheduleID;
		this.availableSeats = availableSeats;
		this.date = date;
		this.status = status;
		this.bookings = bookings;
	}
	
	// Parameterized constructor for many to one as it can't contain Set<Bookings> bookings method for it  
	public BusSchedule(int scheduleID, int availableSeats, Date date, Status status) {
		super();
		this.scheduleID = scheduleID;
		this.availableSeats = availableSeats;
		this.date = date;
		this.status = status;
	}
	
	// For many to one relationship between busSchedule to busRoute start
	
	//Get User method created while mapping many to one relationship between busSchedule to busRoute start
    public BusRoute getBusRoute() {
        return busRoute;
    }
    //Set User method created while mapping many to one relationship between busSchedule to busRoute start
    public void setBusRoute(BusRoute busRoute) {
        this.busRoute = busRoute;
    }
	
	// For many to one relationship between busSchedule to busRoute end

	
	// For many to one relationship between busSchedule to busOperator start
	
	//Get User method created while mapping many to one relationship between busSchedule to busOperator start
    public BusOperator getOperator() {
        return operator;
    }
    //Set User method created while mapping many to one relationship between busSchedule to busOperator start
    public void setOperator(BusOperator operator) {
        this.operator = operator;
    }
	
	// For many to one relationship between busSchedule to busOperator end
    
    
    //FOr one to many relationship between BusSchedule to booking

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setBusSchedule(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setBusSchedule(null);
    }
    //FOr one to many relationship between BusSchedule to booking


	//Getters and Setters Start
	public int getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
    //Getters and Setters End

	//ToString Method
	@Override
	public String toString() {
		return "BusSchedule [scheduleID=" + scheduleID + ", operatorID=" + ", availableSeats=" + availableSeats + ", date=" + date + ", status=" + status + "]";
	}

}