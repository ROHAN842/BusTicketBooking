package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
