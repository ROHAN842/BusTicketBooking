package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
