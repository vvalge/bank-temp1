package com.bank.transfer.repository;

import com.bank.transfer.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository of transfers by card number
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    /**
     * Метод поиска модели для переводов по номеру карты
     * @param number номер карты по которому переводить деньги
     * @return модель для переводов по номеру карты
     */
    @Query("select u from CardEntity u where u.number=(:number)")
    CardEntity findByNumber(@Param("number") Long number);

}
