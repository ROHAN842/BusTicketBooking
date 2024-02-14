package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusOperator;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.User;
import com.hexaware.fastx.exception.BusRouteNotFoundException;

import java.sql.Time;

@SpringBootTest
 class AdminServiceImpTest {
	
    @Autowired
    IAdminService adminService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDeleteUserAccount() {
        String result = adminService.deleteUserAccount(101);
        assertEquals("User deleted", result);
    }

    @Test
    void testDeleteBusOperatorAccount() {
        String result = adminService.deleteBusOperatorAccount(201);
        assertEquals("Bus Operator deleted", result);
    }

    @Test
    void testManageBookedTickets() {
        List<Booking> bookings = adminService.manageBookedTickets(3);
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }

    @Test
    void testViewAllBusRoutes() {
        List<BusRoute> busRoutes = adminService.viewAllBusRoutes();
        assertNotNull(busRoutes);
        assertTrue(busRoutes.size() > 0);
    }

    @Test
    void testAddBusRoute() {
        // Create a new BusRouteDTO object and set its properties
        BusRouteDTO busRouteDto = new BusRouteDTO();
        busRouteDto.setOrigin("Dewas");
        busRouteDto.setDestination("Indore");
        busRouteDto.setDistanceCovered(35);
     // Set the estimated duration for the bus route
        Time estimatedDuration = Time.valueOf("01:00:00"); // Assuming 1 hours 
        busRouteDto.setEstimatedDuration(estimatedDuration);
        busRouteDto.setRouteDescription("Very easy to travel with great bus facility");

        // Call the addBusRoute method
        BusRoute busRoute = adminService.addBusRoute(busRouteDto);

        // Assert that the returned busRoute is not null
        assertNotNull(busRoute);
    }

    @Test
    void testEditBusRoute() throws BusRouteNotFoundException {
        // Create a new BusRouteDTO object and set its properties
        BusRouteDTO busRouteDto = new BusRouteDTO("Bjuj", "Kutch", 170, Time.valueOf("02:30:00"), "Excellent amenities are provided in this bus");

        // Call the editBusRoute method
        BusRoute busRoute = adminService.editBusRoute(busRouteDto, 202);

        // Assert that the returned busRoute is not null
        assertNotNull(busRoute);
    }


    @Test
    void testRemoveBusRoute() {
        String result = adminService.removeBusRoute(301);
        assertEquals("Bus Route deleted", result);
    }

    @Test
    void testSearchBusRoutes() {
        List<BusRoute> busRoutes = adminService.searchBusRoutes("New Delhi", "Mumbai");
        assertNotNull(busRoutes);
        assertTrue(busRoutes.size() > 0);
    }

    @Test
    void testGetUserById() {
        User user = adminService.getUserById(3);
        assertNotNull(user);
        assertEquals(3, user.getUserId());
        // Add assertions to verify other properties of user
    }

    @Test
    void testGetAllUsers() {
        List<User> users = adminService.getAllUsers();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void testGetBusOperatorById() {
        BusOperator operator = adminService.getBusOperatorById(2);
        assertNotNull(operator);
        assertEquals(2, operator.getOperatorId());
    }

    @Test
    void testGetAllBusOperators() {
        List<BusOperator> operators = adminService.getAllBusOperators();
        assertNotNull(operators);
        assertTrue(operators.size() > 0);
    }
}
