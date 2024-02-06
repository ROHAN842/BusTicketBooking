package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.User;

@SpringBootTest
public class UserServiceImpTest {
	@Autowired
    IUserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testRegisterUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(101);
        userDTO.setUsername("TESTUSER");
        userDTO.setPassword("password21");
        userDTO.setEmail("test@gmail.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setAddress("123 Street, City");
        userDTO.setRegistrationDate(LocalDateTime.now());

        User registeredUser = userService.registerUser(userDTO);

        assertNotNull(registeredUser);
        assertEquals(userDTO.getUserID(), registeredUser.getUserID());
        assertEquals(userDTO.getUsername(), registeredUser.getUsername());
        assertEquals(userDTO.getEmail(), registeredUser.getEmail());
        assertEquals(userDTO.getFirstName(), registeredUser.getFirstName());
        assertEquals(userDTO.getLastName(), registeredUser.getLastName());
        assertEquals(userDTO.getPhoneNumber(), registeredUser.getPhoneNumber());
        assertEquals(userDTO.getAddress(), registeredUser.getAddress());
        assertEquals(userDTO.getRegistrationDate(), registeredUser.getRegistrationDate());
    }

    @Test
    void testLoginUser() {
    }

    @Test
    void testSearchBusRoutes() {
    }

    @Test
    void testGetAutoSuggestions() {
    }

    @Test
    void testGetAvailableSchedules() {
    }

    @Test
    void testGetFaresAndAmenities() {
    }

    @Test
    void testSelectSeats() {
    }

    @Test
    void testCalculateTotalPrice() {
    }

    @Test
    void testBookTickets() {
    }

    @Test
    void testGetBookingHistory() {
    }

    @Test
    void testCancelBooking() {
    }

    @Test
    void testUpdateUserProfile() {
    }

    @Test
    void testChangePassword() {
    }
}
