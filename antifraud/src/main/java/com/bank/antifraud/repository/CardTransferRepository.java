package com.bank.antifraud.repository;

import com.bank.antifraud.model.SuspiciousCardTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CardTransferRepository extends JpaRepository<SuspiciousCardTransfer, Long> {

    /**
     * Метод поиска модели трансфера по карте
     * @param cardTransferId id операции трансфера по карте
     * @return optional модели трансфера по карте
     */
    @Query("select u from SuspiciousCardTransfer u where u.cardTransferId=(:id)")
    Optional<SuspiciousCardTransfer> findByCardTransferId(@Param("id") long cardTransferId);
}
