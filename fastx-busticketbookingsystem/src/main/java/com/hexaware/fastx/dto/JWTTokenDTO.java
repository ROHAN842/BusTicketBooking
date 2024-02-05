package com.hexaware.fastx.dto;

import java.sql.Date;

public class JWTTokenDTO {
	private int tokenId;
	private String token;
	private Date expirationDate;
	public JWTTokenDTO(int tokenId, String token, Date expirationDate) {
		super();
		this.tokenId = tokenId;
		this.token = token;
		this.expirationDate = expirationDate;
	}
	public JWTTokenDTO() {
		super();
	}
	public int getTokenId() {
		return tokenId;
	}
	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	
}