package com.hexaware.fastx.dto;

import java.sql.Date;

import com.hexaware.fastx.entities.Booking.PaymentStatus;
import com.hexaware.fastx.entities.Booking.RefundStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BookingDTO {
	private int bookingID;
    private String seatNumber;
    private Date bookingDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private Date paymentDate;
    @Enumerated(EnumType.STRING)
    private RefundStatus refundStatus;
	public BookingDTO() {
		super();
	}
	public BookingDTO(int bookingID, String seatNumber, Date bookingDate, PaymentStatus paymentStatus, Date paymentDate,
			RefundStatus refundStatus) {
		super();
		this.bookingID = bookingID;
		this.seatNumber = seatNumber;
		this.bookingDate = bookingDate;
		this.paymentStatus = paymentStatus;
		this.paymentDate = paymentDate;
		this.refundStatus = refundStatus;
	}
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
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
    
    
}
