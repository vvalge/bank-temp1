package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AuditDto;
import com.bank.publicinfo.entity.AuditEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.service.interfaces.AuditService;
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

@WebMvcTest(AuditController.class)
class AuditControllerTest {

    private static final String PATH = "/audits";
    private static final Long id = 1L;
    private static final AuditDto dto = new AuditDto();
    private static final AuditEntity entity = new AuditEntity();

    static {
        entity.setId(id);
        dto.setId(id);
    }

    @MockBean
    private AuditService auditService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_getAll_successful() throws Exception {
        when(auditService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getById_successful() throws Exception {
        when(auditService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_createAudit_successful() throws Exception {
        mockMvc.perform(post(PATH)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(auditService).save(dto);
    }

    @Test
    void test_updateAudit_successful() throws Exception {
        mockMvc.perform(put(PATH + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(auditService).update(id, dto);
    }

    @Test
    void test_deleteById_successful() throws Exception {
        mockMvc.perform(delete(PATH + "/?id=" + id))
                .andExpect(status().isNoContent());

        verify(auditService).deleteById(id);
    }

    @Test
    void test_getAll_error() throws Exception {
        when(auditService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getById_error() throws Exception {
        when(auditService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_createAudit_error() throws Exception {
        mockMvc.perform(post(PATH)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(auditService).save(dto);
    }

    @Test
    void test_updateAudit_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(auditService).update(id, dto);

        mockMvc.perform(put(PATH + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_deleteById_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(auditService).deleteById(id);

        mockMvc.perform(delete(PATH + "/?id=" + id))
                .andExpect(status().isNotFound());
    }
}