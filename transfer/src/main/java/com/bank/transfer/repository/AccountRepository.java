package com.bank.transfer.repository;

import com.bank.transfer.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository of transfers by account number
 */
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    /**
     * Метод поиска модели для переводов по номеру счёта
     * @param number номер счета по которому переводить деньги
     * @return модель для переводов по номеру счёта
     */
    @Query("select u from AccountEntity u where u.number=(:number)")
    AccountEntity findByNumber(@Param("number") Long number);
}
