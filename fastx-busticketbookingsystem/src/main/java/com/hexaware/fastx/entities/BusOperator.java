package com.hexaware.fastx.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="Bus_Operator")
public class BusOperator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Min(value = 150, message = "OperatorId must be greater than or equal to 150")
    @Max(value = 1050, message = "OperatorId must be less than or equal to 1050")
	private int operatorId;
	@NotBlank(message = "OperatorUsername is required")
    @Pattern(regexp = "^[a-z]+$", message = "OperatorUsername must contain only lowercase letters")
	private String operatorUsername;
	@NotBlank(message = "OperatorPassword is required")
	private String operatorPassword;
	private String operatorname;
	@NotBlank(message = "EmailId is required")
	@Email(message = "Email should be valid")
	private String emailId;
	private String phoneNumber;
	private Date registrationDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId")
	private Admin admin;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busOperator")
	private Set<JWTToken> jwtToken = new HashSet<JWTToken>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "busOperator")
	private Set<AuditLog> auditLog = new HashSet<AuditLog>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operator")
	private Set<BusSchedule> busSchedule = new HashSet<BusSchedule>();
	
	public BusOperator() {
		
	}


	public BusOperator(int operatorId, String operatorUsername, String operatorPassword, String operatorname,
			String emailId, String phoneNumber, Date registrationDate, Admin admin, Set<JWTToken> jwtToken,
			Set<AuditLog> auditLog, Set<BusSchedule> busSchedule) {
		super();
		this.operatorId = operatorId;
		this.operatorUsername = operatorUsername;
		this.operatorPassword = operatorPassword;
		this.operatorname = operatorname;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
		this.admin = admin;
		this.jwtToken = jwtToken;
		this.auditLog = auditLog;
		this.busSchedule = busSchedule;
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


	@Override
	public String toString() {
		return "BusOperator [operatorId=" + operatorId + ", operatorUsername=" + operatorUsername
				+ ", operatorPassword=" + operatorPassword + ", operatorname=" + operatorname + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", registrationDate=" + registrationDate + "]";
	}
	
	
	
}
