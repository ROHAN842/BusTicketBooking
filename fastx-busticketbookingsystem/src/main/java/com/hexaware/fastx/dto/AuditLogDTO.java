package com.hexaware.fastx.dto;

import java.sql.Date;

public class AuditLogDTO {
	private String ActivityType;
	private Date ActivityTime;
	private String details;
	public AuditLogDTO() {
		super();
	}
	public AuditLogDTO(String activityType, Date activityTime, String details) {
		super();
		ActivityType = activityType;
		ActivityTime = activityTime;
		this.details = details;
	}
	
	public String getActivityType() {
		return ActivityType;
	}
	public void setActivityType(String activityType) {
		ActivityType = activityType;
	}
	public Date getActivityTime() {
		return ActivityTime;
	}
	public void setActivityTime(Date activityTime) {
		ActivityTime = activityTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
