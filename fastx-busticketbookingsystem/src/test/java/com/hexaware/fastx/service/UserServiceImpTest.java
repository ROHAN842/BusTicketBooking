package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.fastx.dto.BookingDTO;
import com.hexaware.fastx.dto.UserDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.BusSchedule.Amenities;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.UserNotFoundException;

@SpringBootTest
public class UserServiceImpTest {
    
    @Autowired
    IUserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testRegisterUser() {
        // Create a new UserDTO object and set its properties
        UserDTO userDto = new UserDTO();
        userDto.setUsername("RAJ_PATEL");
        userDto.setPassword("raj@213");
        userDto.setEmail("raj.patel@example.com");
        userDto.setFirstName("Raj");
        userDto.setLastName("Patel");
        userDto.setPhoneNumber("8224805367");
        userDto.setAddress("45, Alkapuri Dewas");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse("2024-05-19");
            Date registrationDate = new Date(utilDate.getTime());
            userDto.setRegistrationDate(registrationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Call the registerUser method
        User user = userService.registerUser(userDto);

        // Assert that the returned user is not null
        assertNotNull(user);
    }

    @Test
    void testSearchBusRoutes() {
        List<BusRoute> busRoutes = userService.searchBusRoutes("Dewas", "Indore");
        assertNotNull(busRoutes);
        assertTrue(busRoutes.size() > 0);
    }

    @Test
    void testGetAvailableSchedules() {
        List<BusSchedule> schedules = userService.getAvailableSchedules(2); // Assuming this is the ID of an existing route
        assertNotNull(schedules);
        assertTrue(schedules.size() > 0);
    }

    @Test
    void testGetFaresAndAmenities() {
        Map<Integer, Set<Amenities>> faresAndAmenities = userService.getFaresAndAmenities(5); // Assuming this is the ID of an existing schedule
        assertNotNull(faresAndAmenities);
        assertTrue(faresAndAmenities.size() > 0);
    }

    @Test
    void testBookTickets() {
        // Create a new BookingDTO object and set its properties
        BookingDTO bookingDto = new BookingDTO();
        bookingDto.setUserId(53); // Assuming this is the ID of an existing user
        bookingDto.setScheduleId(7); // Assuming this is the ID of an existing schedule
        bookingDto.setTotalNumberOfSeats(5);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse("2024-06-14");
            Date bookingDate = new Date(utilDate.getTime());
            bookingDto.setBookingDate(bookingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse("2024-06-12");
            Date bookingDate = new Date(utilDate.getTime());
            bookingDto.setPaymentDate(bookingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        bookingDto.setPaymentStatus(Booking.PaymentStatus.COMPLETED);
        bookingDto.setRefundStatus(Booking.RefundStatus.PENDING);

        // Call the bookTickets method
        Booking booking = userService.bookTickets(bookingDto);

        // Assert that the returned booking is not null
        assertNotNull(booking);
    }

    @Test
    void testGetBookingHistory() {
        List<Booking> bookings = userService.getBookingHistory(4); // Assuming this is the ID of an existing user
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }

    @Test
    void testCancelBooking() {
        String result = userService.cancelBooking(3); // Assuming this is the ID of an existing booking
        assertEquals("Booking cancelled", result);
    }

    @Test
    void testUpdateUserProfile() throws UserNotFoundException {
        // Create a new UserDTO object and set its properties
        UserDTO userDto = new UserDTO();
        userDto.setUsername("ANKITA_SINGH");
        userDto.setPassword("ankita12334");
        userDto.setEmail("ankita@gmail.com");
        userDto.setFirstName("Ankita");
        userDto.setLastName("Singh");
        userDto.setPhoneNumber("62673089711");
        userDto.setAddress("320, Vijay Nagar Dewas");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse("2024-03-18");
            Date registrationDate = new Date(utilDate.getTime());
            userDto.setRegistrationDate(registrationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userDto.setAdminId(1); // Assuming this is the ID of an existing admin

        // Call the updateUserProfile method
        User updatedUser = userService.updateUserProfile(userDto, 54); // Assuming this is the ID of an existing user

        // Assert that the returned updatedUser is not null
        assertNotNull(updatedUser);
    }

    @Test
    void testChangePassword() throws UserNotFoundException {
        boolean result = userService.changePassword(2, "new_password"); // Assuming this is the ID of an existing user
        assertTrue(result);
    }
}
