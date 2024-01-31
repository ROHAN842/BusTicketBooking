package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {

}
