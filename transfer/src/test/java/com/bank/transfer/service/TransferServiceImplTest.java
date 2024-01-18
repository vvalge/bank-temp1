package com.bank.transfer.service;

import com.bank.transfer.TransferApplication;
import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

/**
 * Класс теста "Сервиса переводов"
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransferApplication.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferServiceImplTest {

    @Qualifier("transferServiceImpl")
    @Autowired
    TransferService service;

    /**
     * Метод теста переводов по номеру счета
     */
    @Transactional
    @Test
    public void transferByAccountNumber() {

        AccountEntity account = new AccountEntity();
        AccountEntity account1 = new AccountEntity();

        account.setNumber(8L);
        account.setAmount(new BigDecimal("10.10"));
        account.setPurpose("Transfer by account number");
        account.setAccountDetailsId(2L);
        service.transferByAccountNumber(account);

        account1.setId(2L);
        account1.setNumber(8L);
        account1.setAmount(new BigDecimal("10.10"));
        account1.setPurpose("Transfer by account number");
        account1.setAccountDetailsId(2L);
        service.transferByAccountNumber(account1);
    }

    /**
     * Метод теста переводов по номеру карты
     */
    @Transactional
    @Test
    public void transferByCardNumber() {

        CardEntity card = new CardEntity();
        CardEntity card1 = new CardEntity();

        card.setNumber(11L);
        card.setAmount(new BigDecimal("10.10"));
        card.setPurpose("Transfer by account number");
        card.setAccountDetailsId(2L);
        service.transferByCardNumber(card);

        card1.setId(2L);
        card1.setNumber(11L);
        card1.setAmount(new BigDecimal("10.10"));
        card1.setPurpose("Transfer by account number");
        card1.setAccountDetailsId(2L);
        service.transferByCardNumber(card1);
    }

    /**
     * Метод теста переводов по номеру телефона
     */
    @Transactional
    @Test
    public void transferByPhoneNumber() {
        PhoneEntity phone = new PhoneEntity();
        PhoneEntity phone1 = new PhoneEntity();

        phone.setNumber(1L);
        phone.setAmount(new BigDecimal("10.10"));
        phone.setPurpose("Transfer by account number");
        phone.setAccountDetailsId(2L);
        service.transferByPhoneNumber(phone);

        phone1.setId(2L);
        phone1.setNumber(1L);
        phone1.setAmount(new BigDecimal("10.10"));
        phone1.setPurpose("Transfer by account number");
        phone1.setAccountDetailsId(2L);
        service.transferByPhoneNumber(phone1);
    }
}
