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

@Slf4j
@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferServiceImpl implements TransferService {

    AuditRepository auditRepository;
    AccountRepository accountRepository;
    CardRepository cardRepository;
    PhoneRepository phoneRepository;

    @Override
    public void saveAudit(AuditEntity auditEntity) {
        auditRepository.save(auditEntity);
    }

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
    @Override
    public void transferByCardNumber(@Valid CardEntity entity) throws EntityNotFoundException {
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
