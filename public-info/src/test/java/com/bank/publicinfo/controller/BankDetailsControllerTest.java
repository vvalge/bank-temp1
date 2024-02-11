package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.service.interfaces.BankDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankDetailsController.class)
class BankDetailsControllerTest {

    private static final String PATH = "/bank-details";
    private static final Long id = 1L;
    private static final Long inn = 2L;
    private static final Long bik = 3L;
    private static final Long kpp = 4L;
    private static final Integer corAccount = 5;
    private static final BankDetailsDto dto = new BankDetailsDto();
    private static final BankDetailsEntity entity = new BankDetailsEntity();

    static {
        entity.setId(id);
        dto.setId(id);
        entity.setInn(inn);
        dto.setInn(inn);
        entity.setBik(bik);
        dto.setBik(bik);
        entity.setKpp(kpp);
        dto.setKpp(kpp);
        entity.setCorAccount(corAccount);
        dto.setCorAccount(corAccount);
    }

    @MockBean
    private BankDetailsService bankDetailsService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_getAll_successful() throws Exception {
        when(bankDetailsService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getById_successful() throws Exception {
        when(bankDetailsService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_getByInn_successful() throws Exception {
        when(bankDetailsService.findByInn(inn)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?inn=" + inn))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.inn", is(inn.intValue())));
    }

    @Test
    void test_getByBik_successful() throws Exception {
        when(bankDetailsService.findByBik(bik)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?bik=" + bik))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bik", is(bik.intValue())));
    }

    @Test
    void test_getByKpp_successful() throws Exception {
        when(bankDetailsService.findByKpp(kpp)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?kpp=" + kpp))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.kpp", is(kpp.intValue())));
    }

    @Test
    void test_getByCorAccount_successful() throws Exception {
        when(bankDetailsService.findByCorAccount(corAccount)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?cor-account=" + corAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.corAccount", is(corAccount)));
    }

    @Test
    void test_createBankDetails_successful() throws Exception {
        mockMvc.perform(post(PATH)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(bankDetailsService).save(dto);
    }

    @Test
    void test_update_successful() throws Exception {
        mockMvc.perform(put(PATH + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(bankDetailsService).update(id, dto);
    }

    @Test
    void test_deleteById_successful() throws Exception {
        mockMvc.perform(delete(PATH + "/?id=" + id))
                .andExpect(status().isNoContent());

        verify(bankDetailsService).deleteById(id);
    }

    @Test
    void test_getAll_error() throws Exception {
        when(bankDetailsService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getById_error() throws Exception {
        when(bankDetailsService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_getByInn_error() throws Exception {
        when(bankDetailsService.findByInn(inn)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?inn=" + inn))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.inn", is(inn.intValue())));
    }

    @Test
    void test_getByBik_error() throws Exception {
        when(bankDetailsService.findByBik(bik)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?bik=" + bik))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bik", is(bik.intValue())));
    }

    @Test
    void test_getByKpp_error() throws Exception {
        when(bankDetailsService.findByKpp(kpp)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?kpp=" + kpp))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.kpp", is(kpp.intValue())));
    }

    @Test
    void test_getByCorAccount_error() throws Exception {
        when(bankDetailsService.findByCorAccount(corAccount)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?cor-account=" + corAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.corAccount", is(corAccount)));
    }

    @Test
    void test_createBankDetails_error() throws Exception {
        mockMvc.perform(post(PATH)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(bankDetailsService).save(dto);
    }

    @Test
    void test_update_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(bankDetailsService).update(id, dto);

        mockMvc.perform(put(PATH + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_deleteById_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(bankDetailsService).deleteById(id);

        mockMvc.perform(delete(PATH + "/?id=" + id))
                .andExpect(status().isNotFound());
    }
}