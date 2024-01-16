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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransferApplication.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferControllerTest {

    MockMvc mvc;
    @Autowired
    AccountDtoFactory accountDtoFactory;
    @Autowired
    CardDtoFactory cardDtoFactory;
    @Autowired
    PhoneDtoFactory phoneDtoFactory;
    @Autowired
    WebApplicationContext context;

    @Before
    public void before() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Transactional
    @Test
    public void transferByAccountNumber() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        AccountEntity account = new AccountEntity();
        AccountDto accountDto1 = new AccountDto();

        account.setNumber(11L);
        account.setAmount(new BigDecimal("10.10"));
        account.setPurpose("Transfer by account number");
        AccountDto accountDto = accountDtoFactory.makeAccountEntityToDto(account);

        accountDto1.setId(11L);
        accountDto1.setNumber(11L);
        accountDto1.setPurpose("asa");
        accountDto1.setAmount(new BigDecimal("11.11"));

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

    @Transactional
    @Test
    public void transferByCardNumber() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        CardEntity card = new CardEntity();
        CardEntity card1 = new CardEntity();

        card.setNumber(11L);
        card.setAmount(new BigDecimal("10.10"));
        card.setPurpose("Transfer by account number");
        CardDto cardDto = cardDtoFactory.makeCardEntityToDto(card);

        card1.setId(12L);
        card1.setNumber(11L);
        card1.setAmount(new BigDecimal("11.11"));
        card1.setPurpose("Transfer by account number");
        CardDto cardDto1 = cardDtoFactory.makeCardEntityToDto(card1);

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

    @Transactional
    @Test
    public void transferByPhoneNumber() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
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