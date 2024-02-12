package com.hexaware.fastx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.BusOperator;
@Repository
public interface BusOperatorRepository extends JpaRepository<BusOperator, Integer> {
	Optional<BusOperator> findByOperatorUsername(String username);
}
