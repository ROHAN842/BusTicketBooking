package com.hexaware.fastx.dto;

import java.sql.Date;

import com.hexaware.fastx.entities.BusSchedule.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class BusScheduleDTO {
	private int scheduleID;
    private int availableSeats;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int operatorId;
    private int routeId;
    
	
    
	public BusScheduleDTO(int scheduleID, int availableSeats, Date date, Status status, int operatorId,
			int routeId) {
		super();
		this.scheduleID = scheduleID;
		this.availableSeats = availableSeats;
		this.date = date;
		this.status = status;
		this.operatorId = operatorId;
		this.routeId = routeId;
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



	public BusScheduleDTO() {
		super();
	}
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
    
    
}