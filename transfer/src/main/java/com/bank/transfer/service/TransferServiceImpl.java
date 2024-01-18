package com.bank.transfer.service;

import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.AuditEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import com.bank.transfer.repository.AccountRepository;
import com.bank.transfer.repository.AuditRepository;
import com.bank.transfer.repository.CardRepository;
import com.bank.transfer.repository.PhoneRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

/**
 * Класс Сервис переводов
 * @see com.bank.transfer.service.TransferService
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferServiceImpl implements TransferService {
    /** Репозиторий аудирования
     * @see com.bank.transfer.repository.AuditRepository
     */
    AuditRepository auditRepository;
    /** Репозиторий модели "AccountEntity"
     * @see com.bank.transfer.repository.AccountRepository
     */
    AccountRepository accountRepository;
    /** Репозиторий модели "CardEntity"
     * @see com.bank.transfer.repository.CardRepository
     */
    CardRepository cardRepository;
    /** Репозитроий модели "PhoneEntity"
     * @see com.bank.transfer.repository.PhoneRepository
     */
    PhoneRepository phoneRepository;

    /**
     * Метод записи аудирования в БД
     * @param auditEntity модель аудирования
     */
    @Override
    public void saveAudit(AuditEntity auditEntity) {
        auditRepository.save(auditEntity);
    }

    /**
     * Метод создания/обновления перевода по номеру счета
     * @param entity модель для перевода по номеру счета
     */
    @Override
    public void transferByAccountNumber(@Valid AccountEntity entity) {
        try {
            AccountEntity oldAccountEntity = accountRepository.findByNumber(entity.getNumber());
            if (oldAccountEntity != null) {
                entity.setId(oldAccountEntity.getId());
            }
            accountRepository.save(entity);
        } catch (EntityNotFoundException e) {
            log.error("EntityNotFoundException: " + e);
            throw new EntityNotFoundException("" + e);
        }
    }

    /**
     * Метод создания/обновления перевода по номеру карты
     * @param entity модель для перевода по номеру карты
     */
    @Override
    public void transferByCardNumber(@Valid CardEntity entity) {
        try {
            CardEntity oldCardEntity = cardRepository.findByNumber(entity.getNumber());
            if (oldCardEntity != null) {
                entity.setId(oldCardEntity.getId());
            }
            cardRepository.save(entity);
        } catch (EntityNotFoundException e) {
            log.error("EntityNotFoundException: " + e);
            throw new EntityNotFoundException("" + e);
        }

    }

    /**
     * Метод создания перевода по номеру телефона
     * @param entity сущность для перевода по номеру телефона
     */
    @Override
    public void transferByPhoneNumber(@Valid PhoneEntity entity) {
        try{
            phoneRepository.save(entity);
        } catch (EntityNotFoundException e) {
            log.error("EntityNotFoundException: " + e);
            throw new EntityNotFoundException("" + e);
        }
    }
}
