package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
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
import com.hexaware.fastx.exception.BookingNotFoundException;
import com.hexaware.fastx.exception.BusRouteNotFoundException;
import com.hexaware.fastx.exception.ScheduleNotFoundException;
import com.hexaware.fastx.exception.SeatUnavailableException;

@SpringBootTest
class BusOperatorServiceImpTest {

    @Autowired
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
    void testEditBusRoute() throws BusRouteNotFoundException {
        BusRouteDTO busRouteDto = new BusRouteDTO();
        // Set properties of busRouteDto
        int routeId = 1;
        BusRoute busRoute = busOperatorService.editBusRoute(busRouteDto, routeId);
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
    void testEditBusSchedule() throws ScheduleNotFoundException {
        BusScheduleDTO busScheduleDto = new BusScheduleDTO();
        int scheduleId = 1;
        BusSchedule busSchedule = busOperatorService.editBusSchedule(busScheduleDto, scheduleId);
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
    void testSetFares() throws ScheduleNotFoundException {
        boolean result = busOperatorService.updateFares(201, BigDecimal.valueOf(50));
        assertTrue(result);
    }

    @Test
    void testManageSeatAvailability() throws SeatUnavailableException {
        boolean result = busOperatorService.manageSeatAvailability(301);
        assertTrue(result);
    }

    @Test
    void testViewBookedTickets() {
        List<Booking> bookings = busOperatorService.viewBookedTickets(401);
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }

    @Test
    void testRefundCancelledTickets() throws BookingNotFoundException {
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
