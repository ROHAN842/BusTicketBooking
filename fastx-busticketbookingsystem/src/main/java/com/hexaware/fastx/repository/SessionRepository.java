package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.Session;
@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}
