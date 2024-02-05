package com.hexaware.fastx.dto;

import java.sql.Date;

public class SessionDTO {
	private int sessionID; 
    private String token; // JWT Token
    private Date loginTime;
    private Date logoutTime;
	public SessionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SessionDTO(int sessionID, String token, Date loginTime, Date logoutTime) {
		super();
		this.sessionID = sessionID;
		this.token = token;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}
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
    
    
}