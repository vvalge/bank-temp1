package com.bank.antifraud.repository;

import com.bank.antifraud.model.SuspiciousAccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AccountTransferRepository extends JpaRepository<SuspiciousAccountTransfer, Long> {

    /**
     * Метод поиска модели трансфера из аккаунта
     * @param accountTransferId id операции трансфера из аккаунта
     * @return optional модели трансфера из аккаунта
     */
    @Query("select u from SuspiciousAccountTransfer u where u.accountTransferId=(:id)")
    Optional<SuspiciousAccountTransfer> findByAccountTransferId(@Param("id") long accountTransferId);


}
