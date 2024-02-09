package com.bank.profile.repository;

import com.bank.profile.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link Registration}
 * Предоставляет методы для взаимодействия с базой данных.
 */
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
