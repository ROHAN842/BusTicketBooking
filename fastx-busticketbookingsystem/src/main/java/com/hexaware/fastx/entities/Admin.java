package com.hexaware.fastx.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Admin")

public class Admin {
	@Id
	private int adminId;
	private String adminUsername;
	private String adminPassword;
	private String emailId;
	private String phoneNo;
	private Date registrationDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
	private Set<User> user = new HashSet<User>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
	private Set<BusOperator> busOperator = new HashSet<BusOperator>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
	private Set<JWTToken> jwtToken = new HashSet<JWTToken>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
	private Set<AuditLog> auditLog = new HashSet<AuditLog>();
	
	public Admin() {
		
	}

	

	public Admin(int adminId, String adminUsername, String adminPassword, String emailId, String phoneNo,
			Date registrationDate, Set<User> user, Set<BusOperator> busOperator, Set<JWTToken> jwtToken,
			Set<AuditLog> auditLog) {
		super();
		this.adminId = adminId;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.registrationDate = registrationDate;
		this.user = user;
		this.busOperator = busOperator;
		this.jwtToken = jwtToken;
		this.auditLog = auditLog;
	}



	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Set<BusOperator> getBusOperator() {
		return busOperator;
	}

	public void setBusOperator(Set<BusOperator> busOperator) {
		this.busOperator = busOperator;
	}



	public Set<JWTToken> getJwtToken() {
		return jwtToken;
	}



	public void setJwtToken(Set<JWTToken> jwtToken) {
		this.jwtToken = jwtToken;
	}



	public Set<AuditLog> getAuditLog() {
		return auditLog;
	}



	public void setAuditLog(Set<AuditLog> auditLog) {
		this.auditLog = auditLog;
	}



	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword
				+ ", emailId=" + emailId + ", phoneNo=" + phoneNo + ", registrationDate=" + registrationDate + ", user="
				+ user + ", busOperator=" + busOperator + "]";
	}
	
	
}

