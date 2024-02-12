package com.hexaware.fastx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByAdminUsername(String username);
}
