package com.hexaware.fastx.dto;

import java.sql.Date;

import com.hexaware.fastx.entities.Booking.PaymentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BookingDTO {
	private int totalNumberOfSeats;
    private Date bookingDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private Date paymentDate;
    private int userId;
    private int scheduleId;
    
    
	public BookingDTO() {
		super();
	}
	public BookingDTO(int totalNumberOfSeats, Date bookingDate, PaymentStatus paymentStatus, Date paymentDate) {
		super();
		this.totalNumberOfSeats = totalNumberOfSeats;
		this.bookingDate = bookingDate;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
    
    
}
