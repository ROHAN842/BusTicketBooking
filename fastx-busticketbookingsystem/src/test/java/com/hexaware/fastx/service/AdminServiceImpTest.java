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

@SpringBootTest
public class AdminServiceImpTest {
	
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
        List<Booking> bookings = adminService.manageBookedTickets(101);
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
        BusRouteDTO busRouteDto = new BusRouteDTO();
        // Set properties of busRouteDto
        BusRoute busRoute = adminService.addBusRoute(busRouteDto);
        assertNotNull(busRoute);
    }

    @Test
    void testEditBusRoute() {
        BusRouteDTO busRouteDto = new BusRouteDTO();
        // Set properties of busRouteDto
        BusRoute busRoute = adminService.editBusRoute(busRouteDto);
        assertNotNull(busRoute);
    }

    @Test
    void testRemoveBusRoute() {
        String result = adminService.removeBusRoute(301);
        assertEquals("Bus Route deleted", result);
    }

    @Test
    void testSearchBusRoutes() {
        List<BusRoute> busRoutes = adminService.searchBusRoutes("Origin", "Destination");
        assertNotNull(busRoutes);
        assertTrue(busRoutes.size() > 0);
    }

    @Test
    void testGetUserById() {
        User user = adminService.getUserById(101);
        assertNotNull(user);
        assertEquals(101, user.getUserId());
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
        BusOperator operator = adminService.getBusOperatorById(201);
        assertNotNull(operator);
        assertEquals(201, operator.getOperatorId());
    }

    @Test
    void testGetAllBusOperators() {
        List<BusOperator> operators = adminService.getAllBusOperators();
        assertNotNull(operators);
        assertTrue(operators.size() > 0);
    }
}
