package com.hexaware.fastx.dto;

import java.sql.Date;

public class BusOperatorDTO {
	private String operatorUsername;
	private String operatorPassword;
	private String operatorName;
	private String emailId;
	private String phoneNumber;
	private Date registrationDate;
	private String roles;
	public BusOperatorDTO() {
		super();
	}
	public BusOperatorDTO(String operatorUsername, String operatorPassword, String operatorName,
			String emailId, String phoneNumber, Date registrationDate, String roles) {
		super();
		this.operatorUsername = operatorUsername;
		this.operatorPassword = operatorPassword;
		this.operatorName = operatorName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
		this.roles = roles;
	}
	
	public String getOperatorUsername() {
		return operatorUsername;
	}
	public void setOperatorUsername(String operatorUsername) {
		this.operatorUsername = operatorUsername;
	}
	public String getOperatorPassword() {
		return operatorPassword;
	}
	public void setOperatorPassword(String operatorPassword) {
		this.operatorPassword = operatorPassword;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
