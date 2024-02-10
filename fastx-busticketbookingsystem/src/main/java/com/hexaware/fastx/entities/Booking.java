package com.hexaware.fastx.entities;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


//Since we're already mapping the User entity using @ManyToOne association, 
//you don't need to manually include the userID field in the Booking entity.
@Entity
@Table(name="Booking")

public class Booking { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingID;
	private int totalNumberOfSeats;
    private Date bookingDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private Date paymentDate;
    @Enumerated(EnumType.STRING)
    private RefundStatus refundStatus;
     
    //for many to one relationship between booking to user 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;
    
    //for many to one relationship between booking to busSchedule 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scheduleID")
    private BusSchedule busSchedule;

    
    
//    The cascade attribute determines how changes to an entity should be propagated to associated entities. In a many-to-one 
//    relationship, it specifies what should happen to the associated User entity when a change is made to the Booking entity.
  
//    If we want operations such as persist, merge, remove, and refresh to be cascaded from the Booking entity to the associated User 
//    entity,then including cascade = CascadeType.ALL is appropriate. This means that when you perform any of these operations on 
//    a Booking, the corresponding operation will be cascaded to the associated User    
    
    // Enum for PaymentStatus
    public enum PaymentStatus {
        PENDING, COMPLETED
    }

    // Enum for RefundStatus
    public enum RefundStatus {
        PENDING, PROCESSED, NOT_REQUIRED
    }

    //Default Constructor
	public Booking() {
		super();
	}

	//Parameterized Constructor
	public Booking(int bookingID, int totalNumberOfSeats, Date bookingDate, PaymentStatus paymentStatus,
			Date paymentDate, RefundStatus refundStatus) {
		super();
		this.bookingID = bookingID;
		this.totalNumberOfSeats = totalNumberOfSeats;
		this.bookingDate = bookingDate;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.refundStatus = refundStatus;
	}

	//Get User method created while mapping many to one relationship between booking to user
    public User getUser() {
        return user;
    }

	//Set User method created while mapping many to one relationship between booking to user
    public void setUser(User user) {
        this.user = user;
    }
	
    public BusSchedule getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(BusSchedule busSchedule) {
        this.busSchedule = busSchedule;
    }

	//Getters and Setters Start

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	public int getTotalNumberOfSeats() {
		return totalNumberOfSeats;
	}

	public void setTotalNumberOfSeats(int totalNumberOfSeats) {
		this.totalNumberOfSeats = totalNumberOfSeats;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public RefundStatus getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(RefundStatus refundStatus) {
		this.refundStatus = refundStatus;
	}

	//Getters and Setters End

	//ToString Method
	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ", totalNumberOfSeats=" + totalNumberOfSeats + ", bookingDate="
				+ bookingDate + ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate + ", refundStatus="
				+ refundStatus + ", user=" + user + ", busSchedule=" + busSchedule + "]";
	}
}
