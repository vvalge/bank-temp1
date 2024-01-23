package com.bank.transfer.repository;

import com.bank.transfer.model.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository of translations by phone number
 */
@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
}
