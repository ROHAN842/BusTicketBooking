package com.hexaware.fastx.dto;

import java.sql.Date;

public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private Date registrationDate;
    private int adminId;
    
	
	public UserDTO(String username, String password, String email, String firstName, String lastName,
			String phoneNumber, String address, Date registrationDate, int adminId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.registrationDate = registrationDate;
		this.adminId = adminId;
	}
	public UserDTO() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdmin(int adminId) {
		this.adminId = adminId;
	}

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    
}
