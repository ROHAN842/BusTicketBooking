package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
