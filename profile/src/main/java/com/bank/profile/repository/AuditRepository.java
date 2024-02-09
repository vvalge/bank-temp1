package com.bank.profile.repository;

import com.bank.profile.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Репозиторий для {@link Audit
 * Предоставляет методы для взаимодействия с базой данных.}
 */
public interface AuditRepository extends JpaRepository<Audit, Long> {
}
