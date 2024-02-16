package com.bank.profile.controller;

import com.bank.profile.services.interfase.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bank.profile.dto.AuditDto;
import com.bank.profile.model.Audit;

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
class AuditControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuditService auditService;

    @InjectMocks
    private AuditController auditController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(auditController).build();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        Audit audit = new Audit();
        audit.setId(id);
        when(auditService.getById(id)).thenReturn(audit);

        mockMvc.perform(get("/audit/getById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testCreate() throws Exception {
        AuditDto auditDto = new AuditDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String auditDtoJson = objectMapper.writeValueAsString(auditDto);

        mockMvc.perform(post("/audit/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        AuditDto auditDto = new AuditDto();
        ObjectMapper objectMapper = new ObjectMapper();
        String auditDtoJson = objectMapper.writeValueAsString(auditDto);

        mockMvc.perform(post("/audit/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(auditDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;
        doNothing().when(auditService).deleteById(id);

        mockMvc.perform(delete("/audit/delete/{id}", id))
                .andExpect(status().isOk());
    }
}
