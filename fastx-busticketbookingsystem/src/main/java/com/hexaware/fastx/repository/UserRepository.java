package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Modifying
	@Query(value = "update user set password = ?1 where user_id = ?2", nativeQuery = true)
	int updatePassword(String newPassword, int userId);
}
