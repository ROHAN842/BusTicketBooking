package com.hexaware.fastx.entities;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Bus_Route")
public class BusRoute {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int routeID;
	@NotBlank
    private String origin;
	@NotBlank
    private String destination;
    private int distanceCovered;
    private Time estimatedDuration;
    private String routeDescription;
    
    //One to many relationship from BusRoute to BusSchedule 
    @OneToMany(mappedBy = "busRoute", cascade = CascadeType.ALL)
    private Set<BusSchedule> schedules = new HashSet<>();
    

    //Default Constructor
	public BusRoute() {
		super();
	}
	
    public BusRoute(int routeID, String origin, String destination, int distanceCovered, Time estimatedDuration,
			String routeDescription, Set<BusSchedule> schedules) {
		super();
		this.routeID = routeID;
		this.origin = origin;
		this.destination = destination;
		this.distanceCovered = distanceCovered;
		this.estimatedDuration = estimatedDuration;
		this.routeDescription = routeDescription;
		this.schedules = schedules;
	}

	
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
	
    public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
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

	@Override
	public String toString() {
		return "BusRoute [routeID=" + routeID + ", origin=" + origin + ", destination=" + destination
				+ ", distanceCovered=" + distanceCovered + ", estimatedDuration=" + estimatedDuration
				+ ", routeDescription=" + routeDescription;
	}
	
}
