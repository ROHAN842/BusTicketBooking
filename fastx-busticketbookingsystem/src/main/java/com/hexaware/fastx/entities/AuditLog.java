package com.hexaware.fastx.entities;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Audit_Log")

public class AuditLog { 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int auditLogId;
	private String ActivityType;
	private Date ActivityTime;
	private String details;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId")
	private Admin admin;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BusOperatorId")
	private BusOperator busOperator;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UserId")
	private User user;
	
	public AuditLog() {
		
	}

	public AuditLog(int auditLogId, String activityType, Date activityTime, String details) {
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public BusOperator getBusOperator() {
		return busOperator;
	}

	public void setBusOperator(BusOperator busOperator) {
		this.busOperator = busOperator;
	}

	public void setUser(User user2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "AuditLog [auditLogId=" + auditLogId + ", ActivityType=" + ActivityType + ", ActivityTime="
				+ ActivityTime + ", details=" + details + ", admin=" + admin + ", busOperator=" + busOperator
				+ ", user=" + user + "]";
	}
	
	
	
	
}