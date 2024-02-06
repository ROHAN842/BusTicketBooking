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
	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
