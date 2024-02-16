package com.bank.profile.controller;


import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.services.interfase.AccountDetailsIdService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bank.profile.model.AccountDetailsId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AccountDetailsIdControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountDetailsIdService accountDetailsIdService;

    @InjectMocks
    private AccountDetailsIdController accountDetailsIdController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountDetailsIdController).build();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        AccountDetailsId accountDetailsId = new AccountDetailsId();
        accountDetailsId.setId(id);
        when(accountDetailsIdService.getById(id)).thenReturn(accountDetailsId);

        mockMvc.perform(get("/account/details/getById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testCreate() throws Exception {
        AccountDetailsIdDto accountDetailsIdDto = new AccountDetailsIdDto();
        accountDetailsIdDto.setAccountId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String accountDetailsIdDtoJson = objectMapper.writeValueAsString(accountDetailsIdDto);

        mockMvc.perform(post("/account/details/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDetailsIdDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        AccountDetailsIdDto accountDetailsIdDto = new AccountDetailsIdDto();
        accountDetailsIdDto.setAccountId(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String accountDetailsIdDtoJson = objectMapper.writeValueAsString(accountDetailsIdDto);

        mockMvc.perform(post("/account/details/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accountDetailsIdDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;
        doNothing().when(accountDetailsIdService).deleteById(id);

        mockMvc.perform(delete("/account/details/delete/{id}", id))
                .andExpect(status().isOk());
    }
}
