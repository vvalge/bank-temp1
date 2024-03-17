package com.bank.antifraud.repository;

import com.bank.antifraud.model.TransferAudit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс репозитория для взаимодействия с данными аудита аккаунтов в базе данных
 */
public interface AuditRepository extends JpaRepository<TransferAudit, Long> {

}

