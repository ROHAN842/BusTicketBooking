package com.hexaware.fastx.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="Bus_Operator")
public class BusOperator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Max(value = 1050, message = "OperatorId must be less than or equal to 1050")
	private int operatorId;
	@NotBlank(message = "OperatorUsername is required")
    @Pattern(regexp = "^[a-z]+$", message = "OperatorUsername must contain only lowercase letters")
	private String operatorUsername;
    @NotBlank(message = "OperatorPassword is required")
	private String operatorPassword;
	private String operatorName;
	@NotBlank(message = "EmailId is required")
	@Email(message = "Email should be valid")
	private String emailId;
	private String phoneNumber;
	private Date registrationDate;
	private String roles;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	@JsonIgnore
	private Admin admin;
	
	public BusOperator(Admin admin) {
		this.admin = admin;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busOperator")
	private Set<JWTToken> jwtToken = new HashSet<JWTToken>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busOperator")
	private Set<AuditLog> auditLog = new HashSet<AuditLog>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operator")
	private Set<BusSchedule> busSchedule = new HashSet<BusSchedule>();
	
	public BusOperator() {
		
	}


	public BusOperator(int operatorId, String operatorUsername, String operatorPassword, String operatorName,
			String emailId, String phoneNumber, Date registrationDate, Admin admin, Set<JWTToken> jwtToken,
			Set<AuditLog> auditLog, Set<BusSchedule> busSchedule, String roles) {
		super();
		this.operatorId = operatorId;
		this.operatorUsername = operatorUsername;
		this.operatorPassword = operatorPassword;
		this.operatorName = operatorName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
		this.admin = admin;
		this.jwtToken = jwtToken;
		this.auditLog = auditLog;
		this.busSchedule = busSchedule;
		this.roles = roles;
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

	public String getoperatorName() {
		return operatorName;
	}

	public void setoperatorName(String operatorName) {
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
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


	public Set<BusSchedule> getBusSchedule() {
		return busSchedule;
	}


	public void setBusSchedule(Set<BusSchedule> busSchedule) {
		this.busSchedule = busSchedule;
	}

	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "BusOperator [operatorId=" + operatorId + ", operatorUsername=" + operatorUsername
				+ ", operatorPassword=" + operatorPassword + ", operatorName=" + operatorName + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", registrationDate=" + registrationDate + "]";
	}
	
	
	
}
