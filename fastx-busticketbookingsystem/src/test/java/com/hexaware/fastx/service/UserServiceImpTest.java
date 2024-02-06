package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
=======
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.User;
<<<<<<< HEAD

@SpringBootTest
public class UserServiceImpTest {
	@Autowired
=======
import com.hexaware.fastx.service.IUserService;

@SpringBootTest
class UserServiceImpTest {

    @Autowired
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
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
<<<<<<< HEAD
        assertEquals(userDTO.getUserID(), registeredUser.getUserID());
=======
        assertEquals(userDTO.getUserID(), registeredUser.getUserId());
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
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
