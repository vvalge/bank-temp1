package com.bank.profile.repository;

import com.bank.profile.model.ActualRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link ActualRegistration}
 * Предоставляет методы для взаимодействия с базой данных.
 */
public interface ActualRegistrationRepository extends JpaRepository<ActualRegistration, Long> {
}
