package com.bank.antifraud.repository;

import com.bank.antifraud.model.SuspiciousPhoneTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional
public interface PhoneTransferRepository extends JpaRepository<SuspiciousPhoneTransfer, Long> {
    /**
     * Метод поиска модели трансфера с телефона
     * @param phoneTransferId id операции трансфера с телефона
     * @return optional модели трансфера с телефона
     */
    @Query("select u from SuspiciousPhoneTransfer u where u.phoneTransferId=(:id)")
    Optional<SuspiciousPhoneTransfer> findByPhoneTransferId(@Param("id") long phoneTransferId);
}
