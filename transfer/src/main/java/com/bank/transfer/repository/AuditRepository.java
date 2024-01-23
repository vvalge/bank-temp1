package com.bank.transfer.repository;

import com.bank.transfer.model.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Audit Model Repository interface
 */
@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}
