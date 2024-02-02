package com.bank.profile.repository;

import com.bank.profile.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Репозиторий для {@link Passport}
 * Предоставляет методы для взаимодействия с базой данных.
 */
public interface PassportRepository extends JpaRepository<Passport, Long> {
}
