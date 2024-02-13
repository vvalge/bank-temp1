package com.bank.profile.controller;


import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.model.Registration;

import com.bank.profile.services.interfase.RegistrationService;
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
class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RegistrationService registrationService;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        Registration registration = new Registration();
        registration.setId(id);
        when(registrationService.getById(id)).thenReturn(registration);

        mockMvc.perform(get("/registration/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testCreate() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto();
        mockMvc.perform(post("/registration/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        RegistrationDto registrationDto = new RegistrationDto();
        mockMvc.perform(post("/registration/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;
        doNothing().when(registrationService).deleteById(id);

        mockMvc.perform(delete("/registration/delete/{id}", id))
                .andExpect(status().isOk());
    }
}
