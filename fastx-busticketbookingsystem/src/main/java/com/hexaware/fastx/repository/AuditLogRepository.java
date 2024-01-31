package com.hexaware.fastx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.fastx.entities.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {

}
