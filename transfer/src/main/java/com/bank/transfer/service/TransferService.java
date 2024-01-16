package com.bank.transfer.service;

import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.AuditEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import org.springframework.stereotype.Service;

@Service
public interface TransferService {
    void saveAudit(AuditEntity auditEntity);
    void transferByAccountNumber(AccountEntity accountEntity);
    void transferByCardNumber(CardEntity cardEntity);
    void transferByPhoneNumber(PhoneEntity phoneEntity);
}
