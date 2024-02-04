package com.hexaware.fastx.dto;

import java.sql.Date;

public class AuditLogDTO {
	private int auditLogId;
	private String ActivityType;
	private Date ActivityTime;
	private String details;
	public AuditLogDTO() {
		super();
	}
	public AuditLogDTO(int auditLogId, String activityType, Date activityTime, String details) {
		super();
		this.auditLogId = auditLogId;
		ActivityType = activityType;
		ActivityTime = activityTime;
		this.details = details;
	}
	public int getAuditLogId() {
		return auditLogId;
	}
	public void setAuditLogId(int auditLogId) {
		this.auditLogId = auditLogId;
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
