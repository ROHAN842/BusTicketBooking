package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
<<<<<<< HEAD
=======
import java.sql.Date;
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.dto.BusScheduleDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;

@SpringBootTest
<<<<<<< HEAD
public class BusOperatorServiceImpTest {
	@Autowired
=======
class BusOperatorServiceImpTest {

    @Autowired
>>>>>>> 093fb64fcedde451b3a6440c985ce3047e7fcce4
    IBusOperatorService busOperatorService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddBusRoute() {
        BusRouteDTO busRouteDto = new BusRouteDTO();
        BusRoute busRoute = busOperatorService.addBusRoute(busRouteDto);
        assertNotNull(busRoute);
    }

    @Test
    void testEditBusRoute() {
        BusRouteDTO busRouteDto = new BusRouteDTO();
        // Set properties of busRouteDto
        BusRoute busRoute = busOperatorService.editBusRoute(busRouteDto);
        assertNotNull(busRoute);
        // Add assertions to verify properties of busRoute
    }

    @Test
    void testRemoveBusRoute() {
        String result = busOperatorService.removeBusRoute(301);
        assertEquals("Bus Route deleted", result);
    }

    @Test
    void testSearchBusRoutes() {
        List<BusRoute> busRoutes = busOperatorService.searchBusRoutes("Origin", "Destination");
        assertNotNull(busRoutes);
        assertTrue(busRoutes.size() > 0);
    }

    @Test
    void testAddBusSchedule() {
        BusScheduleDTO busScheduleDto = new BusScheduleDTO();
        // Set properties of busScheduleDto
        BusSchedule busSchedule = busOperatorService.addBusSchedule(busScheduleDto);
        assertNotNull(busSchedule);
        // Add assertions to verify properties of busSchedule
    }

    @Test
    void testEditBusSchedule() {
        BusScheduleDTO busScheduleDto = new BusScheduleDTO();
        BusSchedule busSchedule = busOperatorService.editBusSchedule(busScheduleDto);
        assertNotNull(busSchedule);
    }

    @Test
    void testRemoveBusSchedule() {
        String result = busOperatorService.removeBusSchedule(401);
        assertEquals("Bus Schedule removed", result);
    }

    @Test
    void testGetAvailableSchedules() {
        List<BusSchedule> busSchedules = busOperatorService.getAvailableSchedules(101);
        assertNotNull(busSchedules);
        assertTrue(busSchedules.size() > 0);
    }

    @Test
    void testSetFares() {
        boolean result = busOperatorService.setFares(201, BigDecimal.valueOf(50));
        assertTrue(result);
    }

    @Test
    void testManageSeatAvailability() {
        boolean result = busOperatorService.manageSeatAvailability(301, 40);
        assertTrue(result);
    }

    @Test
    void testViewBookedTickets() {
        List<Booking> bookings = busOperatorService.viewBookedTickets(401);
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }

    @Test
    void testRefundCancelledTickets() {
        boolean result = busOperatorService.refundCancelledTickets(501);
        assertTrue(result);
    }

    @Test
    void testGetBookingHistory() {
        List<Booking> bookings = busOperatorService.getBookingHistory(101);
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }
}
