package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
