package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.BusSchedule;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {

}
