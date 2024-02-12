package com.hexaware.fastx.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.fastx.dto.BusRouteDTO;
import com.hexaware.fastx.dto.BusScheduleDTO;
import com.hexaware.fastx.entities.Booking;
import com.hexaware.fastx.entities.Booking.RefundStatus;
import com.hexaware.fastx.entities.BusRoute;
import com.hexaware.fastx.entities.BusSchedule;
import com.hexaware.fastx.entities.BusSchedule.Amenities;
import com.hexaware.fastx.entities.BusSchedule.BusType;
import com.hexaware.fastx.entities.BusSchedule.Status;
import com.hexaware.fastx.exception.BookingNotFoundException;
import com.hexaware.fastx.exception.BusRouteNotFoundException;
import com.hexaware.fastx.exception.ScheduleNotFoundException;
import com.hexaware.fastx.exception.SeatUnavailableException;
import com.hexaware.fastx.repository.BookingRepository;
import com.hexaware.fastx.repository.BusRouteRepository;
import com.hexaware.fastx.repository.BusScheduleRepository;

@SpringBootTest
class BusOperatorServiceImpTest {

    @Autowired
    IBusOperatorService busOperatorService;
    

    @Autowired
    private BusRouteRepository busRouteRepo;

    @Autowired
    private BusOperatorServiceImp busOperator;
    
    @Autowired
    private BookingRepository bookingRepo;
    
    
    @Autowired
    private BusScheduleRepository busScheduleRepo;
    
    
    @BeforeEach
    void setUp() {
    }

    @Test
    void testAddBusRoute() {
        // Arrange
        BusRouteDTO busRouteDto = new BusRouteDTO();
        busRouteDto.setOrigin("Origin");
        busRouteDto.setDestination("Destination");
        busRouteDto.setDistanceCovered(100); // Set some values for distance and duration
        long durationInMinutes = 120;
        long hours = durationInMinutes / 60;
        long minutes = durationInMinutes % 60;
        String timeString = String.format("%02d:%02d:00", hours, minutes);
        Time estimatedDuration = Time.valueOf(timeString);
        busRouteDto.setEstimatedDuration(estimatedDuration);

        busRouteDto.setRouteDescription("Description");

        // Act
        BusRoute busRoute = busOperatorService.addBusRoute(busRouteDto);

        // Assert
        assertNotNull(busRoute);
        assertEquals("Origin", busRoute.getOrigin()); // Add more assertions as needed
        assertEquals("Destination", busRoute.getDestination());
        assertEquals(100, busRoute.getDistanceCovered());
        assertEquals(estimatedDuration, busRoute.getEstimatedDuration()); // Compare Time objects
        assertEquals("Description", busRoute.getRouteDescription());
    }



//    @Test
//    void testEditBusRoute() throws BusRouteNotFoundException {
//        BusRouteDTO busRouteDto = new BusRouteDTO();
//        // Set properties of busRouteDto
//        int routeId = 1;
//        BusRoute busRoute = busOperatorService.editBusRoute(busRouteDto, routeId);
//        assertNotNull(busRoute);
//        // Add assertions to verify properties of busRoute
//    }
    
    
    @Test
    void testEditBusRoute() throws BusRouteNotFoundException {
        // Assuming there exists a BusRoute with ID 1 in the database
        int routeId = 6;

        // Fetching the BusRouteDTO from the database
        Optional<BusRoute> existingRouteOptional = busRouteRepo.findById(routeId);
        assertTrue(existingRouteOptional.isPresent(), "Existing BusRoute with ID 1 not found in the database");
        BusRoute existingRoute = existingRouteOptional.get();

        // Creating a BusRouteDTO with details from the existing route
        BusRouteDTO busRouteDto = new BusRouteDTO();
        busRouteDto.setOrigin(existingRoute.getOrigin());
        busRouteDto.setDestination(existingRoute.getDestination());
        busRouteDto.setDistanceCovered(existingRoute.getDistanceCovered());
        busRouteDto.setEstimatedDuration(existingRoute.getEstimatedDuration());
        busRouteDto.setRouteDescription(existingRoute.getRouteDescription());

        // Modifying the properties of busRouteDto according to test scenario
        busRouteDto.setOrigin("New Origin");
        busRouteDto.setDestination("New Destination");
        busRouteDto.setDistanceCovered(100);
        busRouteDto.setEstimatedDuration(Time.valueOf("02:00:00"));
        busRouteDto.setRouteDescription("New Route Description");

        // Calling the method under test
        BusRoute editedRoute = busOperatorService.editBusRoute(busRouteDto, routeId);

        // Assertions
        assertNotNull(editedRoute, "Edited BusRoute is null");
        assertEquals("New Origin", editedRoute.getOrigin(), "Origin is not updated");
        assertEquals("New Destination", editedRoute.getDestination(), "Destination is not updated");

        // Add more assertions to verify other properties of busRoute if needed
    }

    

    @Test
    void testRemoveBusRoute() {
        String result = busOperatorService.removeBusRoute(301);
        assertEquals("Bus Route deleted", result);
    }

    @Test
    void testSearchBusRoutes() {
        List<BusRoute> busRoutes = busOperatorService.searchBusRoutes("Kolkata", "Hyderabad");
        assertNotNull(busRoutes);
        assertTrue(busRoutes.size() > 0);
    }

//    @Test
//    void testAddBusSchedule() {
//        BusScheduleDTO busScheduleDto = new BusScheduleDTO();
//        // Set properties of busScheduleDto
//        BusSchedule busSchedule = busOperatorService.addBusSchedule(busScheduleDto);
//        assertNotNull(busSchedule);
//        // Add assertions to verify properties of busSchedule
//    }
    
    
    @Test
    void testAddBusSchedule() {
        // Create a BusScheduleDTO object with necessary properties
        BusScheduleDTO busScheduleDto = new BusScheduleDTO();
        busScheduleDto.setRouteId(1); // Assuming this is a valid route ID in the database
        busScheduleDto.setOperatorId(1); // Assuming this is a valid operator ID in the database
        busScheduleDto.setBusNumber("XYZ123");
        busScheduleDto.setAvailableSeats(50);
        Date desiredDate = Date.valueOf("2024-02-12");
        busScheduleDto.setDate(desiredDate);
        Time desiredTime = Time.valueOf("08:00:00");
        busScheduleDto.setTimings(desiredTime);
        busScheduleDto.setStatus(Status.ACTIVE);
        Set<Amenities> amenitiesSet = new HashSet<>();
        amenitiesSet.add(Amenities.TV);
        amenitiesSet.add(Amenities.BLANKET);
        amenitiesSet.add(Amenities.WATER_BOTTLE);
        busScheduleDto.setAmenities(amenitiesSet);
        busScheduleDto.setBusType(BusType.SLEEPER_WITH_AC);


        // Call the method under test
        BusSchedule busSchedule = busOperatorService.addBusSchedule(busScheduleDto);

     // Act
        String amenitiesString = amenitiesSet.stream()
                .map(Amenities::toString)
                .collect(Collectors.joining(", "));
        
        Set<Amenities> amenitiestestSet = new HashSet<>();
        amenitiestestSet.add(Amenities.TV);
        amenitiestestSet.add(Amenities.BLANKET);
        amenitiestestSet.add(Amenities.WATER_BOTTLE);
        
        // Assertions
        assertNotNull(busSchedule, "Added BusSchedule is null");
        assertEquals("XYZ123", busSchedule.getBusNumber(), "Bus number mismatch");
        assertEquals(50, busSchedule.getAvailableSeats(), "Available seats mismatch");
        assertEquals(Date.valueOf("2024-02-12"), busSchedule.getDate(), "Date mismatch");
        assertEquals(Time.valueOf("08:00:00"), busSchedule.getTimings(), "Timings mismatch");
        assertEquals(Status.ACTIVE, busSchedule.getStatus(), "Status mismatch");
        assertEquals(amenitiestestSet,amenitiesSet,"Amenities mismatch");
        assertEquals(BusType.SLEEPER_WITH_AC, busSchedule.getBusType(), "Bus type mismatch");

        // Add more assertions to verify other properties of busSchedule if needed
    }

    

//    @Test
//    void testEditBusSchedule() throws ScheduleNotFoundException {
//        BusScheduleDTO busScheduleDto = new BusScheduleDTO();
//        int scheduleId = 1;
//        BusSchedule busSchedule = busOperatorService.editBusSchedule(busScheduleDto, scheduleId);
//        assertNotNull(busSchedule);
//    }
    
    
    @Test
    void testEditBusSchedule() throws ScheduleNotFoundException {
        // Arrange
        BusScheduleDTO busScheduleDto = new BusScheduleDTO();
        busScheduleDto.setBusNumber("ABC123");
        busScheduleDto.setAvailableSeats(50);
        Date desiredDate = Date.valueOf("2024-02-12");
        busScheduleDto.setDate(desiredDate);
        Time desiredTime = Time.valueOf("08:00:00");
        busScheduleDto.setTimings(desiredTime);
        busScheduleDto.setStatus(Status.ACTIVE);
        busScheduleDto.setAmenities(new HashSet<>(Arrays.asList(Amenities.WIFI, Amenities.AC)));
        busScheduleDto.setBusType(BusType.SLEEPER_WITHOUT_AC); 
        busScheduleDto.setRouteId(1); // Set appropriate values for DTO
        
        int scheduleId = 2;
        
        
     // Set the operatorId
        int operatorId = 1; // Replace 123 with the actual operatorId value
        busScheduleDto.setOperatorId(operatorId);
        
        // Add an existing schedule to the in-memory repository
        BusSchedule existingSchedule = new BusSchedule(); 
        busScheduleRepo.save(existingSchedule);

        // Act
        BusSchedule editedSchedule = busOperatorService.editBusSchedule(busScheduleDto, scheduleId);

        // Assert
        assertNotNull(editedSchedule);
        assertEquals(busScheduleDto.getBusNumber(), editedSchedule.getBusNumber());
        assertEquals(busScheduleDto.getAvailableSeats(), editedSchedule.getAvailableSeats());
        assertEquals(busScheduleDto.getDate(), editedSchedule.getDate());
        assertEquals(busScheduleDto.getTimings(), editedSchedule.getTimings());
        assertEquals(busScheduleDto.getStatus(), editedSchedule.getStatus());
        assertEquals(busScheduleDto.getAmenities(), editedSchedule.getAmenities());
        assertEquals(busScheduleDto.getBusType(), editedSchedule.getBusType());
        assertEquals(busScheduleDto.getRouteId(), editedSchedule.getBusRoute().getRouteID());
    }

    @Test
    void testRemoveBusSchedule() {
        String result = busOperatorService.removeBusSchedule(401);
        assertEquals("Bus Schedule removed", result);
    }

    @Test
    void testGetAvailableSchedules() {
        List<BusSchedule> busSchedules = busOperatorService.getAvailableSchedules(1);
        assertNotNull(busSchedules);
        assertTrue(busSchedules.size() > 0);
    }

    @Test
    void testSetFares() throws ScheduleNotFoundException {
        boolean result = busOperatorService.updateFares(202, BigDecimal.valueOf(50));
        assertTrue(result);
    }

    @Test
    void testManageSeatAvailability() throws SeatUnavailableException {
        boolean result = busOperatorService.manageSeatAvailability(302);
        assertTrue(result);
    }

    @Test
    void testViewBookedTickets() {
        List<Booking> bookings = busOperatorService.viewBookedTickets(7);
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }

//    @Test
//    void testRefundCancelledTickets() throws BookingNotFoundException {
//        boolean result = busOperatorService.refundCancelledTickets(102);
//        assertTrue(result);
//    }

    @Test
    void testRefundCancelledTickets_WhenBookingExistsAndRefundStatusIsPending_ExpectTrue() throws BookingNotFoundException {
        // Arrange
        Booking booking = new Booking();
        booking.setRefundStatus(RefundStatus.PENDING);
        booking.setBookingID(1);
        bookingRepo.save(booking);

        // Act
        boolean result = busOperatorService.refundCancelledTickets(1);

        // Assert
        assertTrue(result);
        assertEquals(RefundStatus.PENDING, booking.getRefundStatus());
    }

    @Test
    void testRefundCancelledTickets_WhenBookingDoesNotExist_ExpectException() {
        // Act & Assert
        assertThrows(BookingNotFoundException.class, () -> {
            busOperatorService.refundCancelledTickets(1);
        });
    }

    @Test
    void testRefundCancelledTickets_WhenRefundStatusIsNotPending_ExpectFalse() throws BookingNotFoundException {
        // Arrange
        Booking booking = new Booking();
        booking.setRefundStatus(RefundStatus.PROCESSED);
        booking.setBookingID(1);
        bookingRepo.save(booking);

        // Act
        boolean result = busOperatorService.refundCancelledTickets(1);

        // Assert
        assertFalse(result);
        assertEquals(RefundStatus.PROCESSED, booking.getRefundStatus());
    }
    
    
    @Test
    void testGetBookingHistory() {
        List<Booking> bookings = busOperatorService.getBookingHistory(53);
        assertNotNull(bookings);
        assertTrue(bookings.size() > 0);
    }
    
    
}
