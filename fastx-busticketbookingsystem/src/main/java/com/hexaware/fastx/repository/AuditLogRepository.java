package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastx.entities.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {

}
