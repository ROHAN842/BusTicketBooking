package com.hexaware.fastx.dto;

import java.sql.Date;

public class AdminDTO {
	private String adminUsername;
	private String adminPassword;
	private String emailId;
	private String phoneNo;
	private Date registrationDate;
	public AdminDTO() {
		super();
	}
	public AdminDTO(String adminUsername, String adminPassword, String emailId, String phoneNo,
			Date registrationDate) {
		super();
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.registrationDate = registrationDate;
	}
	
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
}
