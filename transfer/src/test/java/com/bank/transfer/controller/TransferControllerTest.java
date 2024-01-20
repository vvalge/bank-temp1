package com.bank.transfer.controller;

import com.bank.transfer.TransferApplication;
import com.bank.transfer.dto.AccountDto;
import com.bank.transfer.dto.CardDto;
import com.bank.transfer.dto.PhoneDto;
import com.bank.transfer.mapper.AccountDtoFactory;
import com.bank.transfer.mapper.CardDtoFactory;
import com.bank.transfer.mapper.PhoneDtoFactory;
import com.bank.transfer.model.AccountEntity;
import com.bank.transfer.model.CardEntity;
import com.bank.transfer.model.PhoneEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import java.math.BigDecimal;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = TransferApplication.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferControllerTest {

    @Autowired
    AccountDtoFactory accountDtoFactory;
    @Autowired
    CardDtoFactory cardDtoFactory;
    @Autowired
    PhoneDtoFactory phoneDtoFactory;

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    ObjectMapper mapper;

    /**
     * Метод инициализации MockMvc
     */
    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = new ObjectMapper();
    }

    /**
     * Метод теста переводов по номеру счета
     * @throws Exception возможны исключения
     */
    @Transactional
    @Test
    public void transferByAccountNumber() throws Exception {

        AccountEntity account = new AccountEntity();
        account.setNumber(11L);
        account.setAmount(new BigDecimal("10.10"));
        account.setPurpose("Transfer by account number");
        AccountDto accountDto = accountDtoFactory.makeAccountEntityToDto(account);

        AccountDto accountDto1 = new AccountDto();
        accountDto1.setNumber(11L);
        accountDto1.setPurpose("asa");
        accountDto1.setAmount(new BigDecimal("11.11"));
        accountDto1.setId(11L);

        mvc.perform(
                        MockMvcRequestBuilders.post("/account/12")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(accountDto))
                                .param("id", "12"))
                .andExpect(status().isOk())
                .andDo(print());
        mvc.perform(
                        MockMvcRequestBuilders.post("/account/12")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(accountDto1))
                                .param("id", "12"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * Метод теста переводов по номеру карты
     * @throws Exception возможны исключения
     */
    @Transactional
    @Test
    public void transferByCardNumber() throws Exception {

        CardEntity card = new CardEntity();
        card.setNumber(11L);
        card.setAmount(new BigDecimal("10.10"));
        card.setPurpose("Transfer by account number");
        CardDto cardDto = cardDtoFactory.makeCardEntityToDto(card);
        CardDto cardDto1 = new CardDto(1L, 1L, new BigDecimal("10.10"), null, null);

        mvc.perform(
                        MockMvcRequestBuilders.post("/card/12")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(cardDto))
                                .param("id", "12"))
                .andExpect(status().isOk())
                .andDo(print());
        mvc.perform(
                        MockMvcRequestBuilders.post("/card/12")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(cardDto1))
                                .param("id", "12"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * Метод теста переводов по номеру телефона
     * @throws Exception возможны исключения
     */
    @Transactional
    @Test
    public void transferByPhoneNumber() throws Exception {

        PhoneEntity phone = new PhoneEntity();
        phone.setNumber(11L);
        phone.setAmount(new BigDecimal("10.10"));
        phone.setPurpose("Transfer by account number");
        PhoneDto phoneDto = phoneDtoFactory.makePhoneEntityToDto(phone);

        mvc.perform(
                        MockMvcRequestBuilders.post("/phone/11")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(phoneDto))
                                .param("id", "11"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
