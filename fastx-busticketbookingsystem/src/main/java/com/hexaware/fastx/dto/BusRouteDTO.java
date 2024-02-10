package com.hexaware.fastx.dto;

import java.sql.Time;

public class BusRouteDTO {
    private String origin;
    private String destination;
    private int distanceCovered;
    private Time estimatedDuration;
    private String routeDescription;
	public BusRouteDTO() {
		super();
	}
	
	public BusRouteDTO(String origin, String destination, int distanceCovered, Time estimatedDuration,
			String routeDescription) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.distanceCovered = distanceCovered;
		this.estimatedDuration = estimatedDuration;
		this.routeDescription = routeDescription;
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
	public int getDistanceCovered() {
		return distanceCovered;
	}
	public void setDistanceCovered(int distanceCovered) {
		this.distanceCovered = distanceCovered;
	}
	public Time getEstimatedDuration() {
		return estimatedDuration;
	}
	public void setEstimatedDuration(Time estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}
	public String getRouteDescription() {
		return routeDescription;
	}
	public void setRouteDescription(String routeDescription) {
		this.routeDescription = routeDescription;
	}
    
}
