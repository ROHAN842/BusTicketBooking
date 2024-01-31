package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.JWTToken;

public interface JWTTokenRepository extends JpaRepository<JWTToken, Integer> {

}
