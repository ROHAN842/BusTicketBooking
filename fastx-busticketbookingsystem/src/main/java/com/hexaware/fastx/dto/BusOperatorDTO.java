package com.hexaware.fastx.dto;

import java.sql.Date;

public class BusOperatorDTO {
	private int operatorId;
	private String operatorUsername;
	private String operatorPassword;
	private String operatorname;
	private String emailId;
	private String phoneNumber;
	private Date registrationDate;
	public BusOperatorDTO() {
		super();
	}
	public BusOperatorDTO(int operatorId, String operatorUsername, String operatorPassword, String operatorname,
			String emailId, String phoneNumber, Date registrationDate) {
		super();
		this.operatorId = operatorId;
		this.operatorUsername = operatorUsername;
		this.operatorPassword = operatorPassword;
		this.operatorname = operatorname;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
	}
	public int getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
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
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
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
}
