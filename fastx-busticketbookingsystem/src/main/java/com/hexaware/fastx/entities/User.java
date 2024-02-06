package com.hexaware.fastx.entities;

import java.time.LocalDateTime;
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
@Table(name="User")
public class User {
	@Id
	@Min(value=100, message="Id should be greater then 100")
	@Max(value=900, message="Id should be less then 900")
	@GeneratedValue(strategy = GenerationType.AUTO)
   
    private int userId;
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "[A-Z]+", message = "Username must be in uppercase")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private LocalDateTime registrationDate;
    
    //FOr one to many relationship between user to booking
    // Collection of Booking entities, one to many relationship from User to booking 

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<Booking>();
    
    // Collection of JWTToken entities, one to many relationship from User to JWTToken
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<JWTToken> jwtTokens = new HashSet<JWTToken>();

    // Collection of Session entities, one to many relationship from User to Session
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<Session>();

 // Collection of AuditLog entities, one to many relationship from User to AuditLog
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AuditLog> auditLogs = new HashSet<>();
    
    // Many to One 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId")
	private Admin admin;
	
	
    // Default Constructor
	public User() {
		super();
	}
	
	// Get Admin Method
	public Admin getAdmin() {
		return admin;
	}

	// Set Admin Method 
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	//Parameterized Constructor
	public User(int userId, String username, String password, String email, String firstName, String lastName,
			String phoneNumber, String address, LocalDateTime registrationDate, Set<Booking> bookings, Set<JWTToken> jwtTokens,
			Set<Session> sessions, Set<AuditLog> auditLogs) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.registrationDate = registrationDate;
		this.bookings = bookings;
		this.jwtTokens = jwtTokens;
		this.sessions = sessions;
		this.auditLogs = auditLogs;
	}

    //FOr one to many relationship between user to booking

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setUser(this);
    }
    //FOr one to many relationship between user to booking

    
    //FOr one to many relationship between user to JWTToken

    public Set<JWTToken> getJwtTokens() {
        return jwtTokens;
    }

    public void setJwtTokens(Set<JWTToken> jwtTokens) {
        this.jwtTokens = jwtTokens;
    }

    public void addJwtToken(JWTToken jwtToken) {
        jwtTokens.add(jwtToken);
        jwtToken.setUser(this);
    }

    public void removeJwtToken(JWTToken jwtToken) {
        jwtTokens.remove(jwtToken);
        jwtToken.setUser(null);
    }
    
    //FOr one to many relationship between user to JWTToken
    
    
    //FOr one to many relationship between user to Session
    
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
        session.setUser(this);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
        session.setUser(null);
    }
    
    //FOr one to many relationship between user to Session
    
	
    //FOr one to many relationship between user to AuditLog
 
    public Set<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(Set<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }

    public void addAuditLog(AuditLog auditLog) {
        auditLogs.add(auditLog);
        auditLog.setUser(this);
    }

    public void removeAuditLog(AuditLog auditLog) {
        auditLogs.remove(auditLog);
        auditLog.setUser(null);
    }
    
    //FOr one to many relationship between user to AuditLog

	//Getters and Setters Start
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	// Getters and Setters End
	
	
	// ToString Method
	@Override
	public String toString() {
		return "User [userID=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", registrationDate=" + registrationDate + "]";
	}
}
