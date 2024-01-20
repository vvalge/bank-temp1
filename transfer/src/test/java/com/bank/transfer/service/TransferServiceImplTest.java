package com.bank.transfer.service;

import com.bank.transfer.mapper.AccountDtoFactory;
import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.AuditEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import com.bank.transfer.repository.AccountRepository;
import com.bank.transfer.repository.AuditRepository;
import com.bank.transfer.repository.CardRepository;
import com.bank.transfer.repository.PhoneRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

/**
 * Класс теста "Сервиса переводов"
 */
@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferServiceImplTest {
    @Mock
    AccountDtoFactory dtoFactory;
    @Mock
    AuditRepository auditRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    CardRepository cardRepository;
    @Mock
    PhoneRepository phoneRepository;

    @InjectMocks
    TransferServiceImpl service;

    /**
     * Метод теста сохранения AuditEntity
     */
    @Test
    public void saveAuditTest() {

        final AuditEntity entity = mock(AuditEntity.class);
        final AuditEntity audit = new AuditEntity();
        final AccountEntity account = new AccountEntity(1L, 10L, new BigDecimal("101.10"),"Good", 1L);

        audit.setEntityType("AccountEntity");
        audit.setOperationType("transfer");
        audit.setCreatedBy("1L");
        audit.setCreatedAt(ZonedDateTime.now());
        audit.setEntityJson(String.format("%s", dtoFactory.makeAccountEntityToDto(account)));

        service.saveAudit(entity);
        verify(auditRepository).save(entity);
        service.saveAudit(audit);
        verify(auditRepository).save(audit);
    }

    /**
     * Метод теста переводов по номеру счета
     */
    @Test
    public void transferByAccountNumberTest() {

        final AccountEntity entity = mock(AccountEntity.class);
        final AccountEntity account = new AccountEntity();
        final AccountEntity account1 = new AccountEntity();

        account.setNumber(8L);
        account.setAmount(new BigDecimal("10.10"));
        account.setPurpose("Transfer by account number");
        account.setAccountDetailsId(2L);

        account1.setId(2L);
        account1.setNumber(8L);
        account1.setAmount(new BigDecimal("10.10"));
        account1.setPurpose("Transfer by account number");
        account1.setAccountDetailsId(2L);

        service.transferByAccountNumber(entity);
        verify(accountRepository).save(entity);
        service.transferByAccountNumber(account);
        verify(accountRepository).save(account);
        service.transferByAccountNumber(account1);
        verify(accountRepository).save(account1);
    }

    /**
     * Метод теста переводов по номеру карты
     */
    @Test
    public void transferByCardNumberTest() {

        final CardEntity entity = mock(CardEntity.class);
        final CardEntity card = new CardEntity();
        final CardEntity card1 = new CardEntity();

        card.setNumber(11L);
        card.setAmount(new BigDecimal("10.10"));
        card.setPurpose("Transfer by account number");
        card.setAccountDetailsId(2L);

        card1.setId(2L);
        card1.setNumber(11L);
        card1.setAmount(new BigDecimal("10.10"));
        card1.setPurpose("Transfer by account number");
        card1.setAccountDetailsId(2L);

        service.transferByCardNumber(entity);
        verify(cardRepository).save(entity);
        service.transferByCardNumber(card);
        verify(cardRepository).save(card);
        service.transferByCardNumber(card1);
        verify(cardRepository).save(card1);
    }

    /**
     * Метод теста переводов по номеру телефона
     */
    @Test
    public void transferByPhoneNumberTest() {

        final PhoneEntity entity = mock(PhoneEntity.class);
        final PhoneEntity phone = new PhoneEntity();
        final PhoneEntity phone1 = new PhoneEntity();

        phone.setNumber(1L);
        phone.setAmount(new BigDecimal("10.10"));
        phone.setPurpose("Transfer by account number");
        phone.setAccountDetailsId(2L);

        phone1.setId(2L);
        phone1.setNumber(1L);
        phone1.setAmount(new BigDecimal("10.10"));
        phone1.setPurpose("Transfer by account number");
        phone1.setAccountDetailsId(2L);

        service.transferByPhoneNumber(entity);
        verify(phoneRepository).save(entity);
        service.transferByPhoneNumber(phone);
        verify(phoneRepository).save(phone);
        service.transferByPhoneNumber(phone1);
        verify(phoneRepository).save(phone1);
    }
}
