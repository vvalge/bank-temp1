package com.bank.profile.controller;
import com.bank.profile.services.interfase.ActualRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.model.ActualRegistration;

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
class ActualRegistrationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ActualRegistrationService actualRegistrationService;

    @InjectMocks
    private ActualRegistrationController actualRegistrationController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(actualRegistrationController).build();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        ActualRegistration actualRegistration = new ActualRegistration();
        actualRegistration.setId(id);
        when(actualRegistrationService.getById(id)).thenReturn(actualRegistration);

        mockMvc.perform(get("/actual/registration/getById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testCreate() throws Exception {
        ActualRegistrationDto actualRegistrationDto = new ActualRegistrationDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String actualRegistrationDtoJson = objectMapper.writeValueAsString(actualRegistrationDto);

        mockMvc.perform(post("/actual/registration/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actualRegistrationDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        ActualRegistrationDto actualRegistrationDto = new ActualRegistrationDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String actualRegistrationDtoJson = objectMapper.writeValueAsString(actualRegistrationDto);

        mockMvc.perform(post("/actual/registration/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(actualRegistrationDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;
        doNothing().when(actualRegistrationService).deleteById(id);

        mockMvc.perform(delete("/actual/registration/delete/{id}", id))
                .andExpect(status().isOk());
    }
}
