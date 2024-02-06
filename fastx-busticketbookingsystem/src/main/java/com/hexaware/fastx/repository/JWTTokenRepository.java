package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.JWTToken;
@Repository
public interface JWTTokenRepository extends JpaRepository<JWTToken, Integer> {

}
