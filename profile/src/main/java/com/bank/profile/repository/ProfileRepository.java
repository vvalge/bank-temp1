package com.bank.profile.repository;

import com.bank.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link Profile}
 * Предоставляет методы для взаимодействия с базой данных.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
