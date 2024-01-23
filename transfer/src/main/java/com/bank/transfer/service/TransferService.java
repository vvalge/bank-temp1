package com.bank.transfer.service;

import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.AuditEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import org.springframework.stereotype.Service;

/**
 * Interface transfer service
 */
@Service
public interface TransferService {
    /**
     * Метод сохранения "AuditEntity" в базу данных
     * @param auditEntity сущность аудирования
     */
    void saveAudit(AuditEntity auditEntity);

    /**
     * Метод сохранения "AccountEntity" в базу данных
     * @param accountEntity сущность для перевода по номеру счета
     */
    void transferByAccountNumber(AccountEntity accountEntity);

    /**
     * Метод сохранения сущности "CardEntity" в базу данных
     * @param cardEntity сущность для перевода по номеру карты
     */
    void transferByCardNumber(CardEntity cardEntity);

    /**
     * Метод сохранения сущности "PhoneEntity" в базу данных
     * @param phoneEntity сущность для перевода по номеру телефона
     */
    void transferByPhoneNumber(PhoneEntity phoneEntity);
}
