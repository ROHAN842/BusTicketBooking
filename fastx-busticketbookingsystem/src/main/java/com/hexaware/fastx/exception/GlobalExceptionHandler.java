package com.hexaware.fastx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({SeatUnavailableException.class})
	public ResponseEntity<String> seatUnavailableHandler() {
		return new ResponseEntity<>("Seats not available.", HttpStatus.INSUFFICIENT_STORAGE);
	}
	
	@ExceptionHandler({BookingNotFoundException.class})
	public ResponseEntity<String> bookingNotFoundHandler() {
		return new ResponseEntity<>("Bookings with the given ID not found.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ScheduleNotFoundException.class})
	public ResponseEntity<String> scheduleNotFoundHandler() {
		return new ResponseEntity<>("Bus schedule with the given ID not found.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<String> userNotFoundHandler() {
		return new ResponseEntity<>("User with the given ID not found.", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({BusRouteNotFoundException.class})
	public ResponseEntity<String> routeNotFoundHandler() {
		return new ResponseEntity<>("Bus Routes with the given ID not found.", HttpStatus.NOT_FOUND);
	}
}
