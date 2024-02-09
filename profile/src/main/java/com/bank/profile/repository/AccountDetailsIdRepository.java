package com.bank.profile.repository;

import com.bank.profile.model.AccountDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AccountDetailsId}
 *  Предоставляет методы для взаимодействия с базой данных.
 */
public interface AccountDetailsIdRepository extends JpaRepository<AccountDetailsId, Long> {
}
