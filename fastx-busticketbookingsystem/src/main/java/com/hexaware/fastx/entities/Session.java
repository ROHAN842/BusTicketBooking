package com.hexaware.fastx.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Session")

public class Session {
	@Id
    private int sessionID; 
    private String token; // JWT Token
    private Date loginTime;
    private Date logoutTime;
    
    
    //for many to one relationship between session to user 
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    
    
    //Default Constructor
	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public Session(int sessionID, String token, Date loginTime, Date logoutTime) {
		super();
		this.sessionID = sessionID;
		this.token = token;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}
	
	//Get User method created while mapping many to one relationship between Session to user

    public User getUser() {
        return user;
    }
    
    //Set User method created while mapping many to one relationship between Session to user

    public void setUser(User user) {
        this.user = user;
    }
	
	//Getters and Setters Start

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	//Getters and Setters End
	
	
	//ToString Method
	@Override
	public String toString() {
		return "Session [sessionID=" + sessionID + ", token=" + token + ", loginTime="
				+ loginTime + ", logoutTime=" + logoutTime + "]";
	}
}
