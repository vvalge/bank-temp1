package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.CertificateEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.service.interfaces.BankDetailsService;
import com.bank.publicinfo.service.interfaces.CertificateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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

@WebMvcTest(CertificateController.class)
class CertificateControllerTest {

    private static final String PATH = "/bank-details";
    private static final Long id = 1L;
    private static final Long bank_id = 2L;
    private static final CertificateDto dto = new CertificateDto();
    private static final CertificateEntity entity = new CertificateEntity();

    static {
        entity.setId(id);
        dto.setId(id);
    }

    @MockBean
    private CertificateService certificateService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BankDetailsService bankDetailsService;

    @Test
    void test_getAllCertificates_successful() throws Exception {
        when(certificateService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/certificates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getCertificateById_successful() throws Exception {
        when(certificateService.findAllByBankDetailsId(id)).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/{id}" + "/certificates", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_addCertificate_successful() throws Exception {
        mockMvc.perform(post(PATH + "/{id}" + "/certificates", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(certificateService).save(dto);
    }

    @Test
    void test_update_successful() throws Exception {
        mockMvc.perform(put(PATH + "/{bank_id}" + "/certificates" + "/?certificate_id=" + id, bank_id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(certificateService).update(id, dto);
    }

    @Test
    void test_deleteCertificateById_successful() throws Exception {
        mockMvc.perform(delete(PATH + "/{bank_id}" + "/certificates" + "/?certificate_id=" + id, bank_id))
                .andExpect(status().isNoContent());

        verify(certificateService).deleteByCertificateIdAndBankDetailsId(id, bank_id);
    }

    @Test
    void test_getAllCertificates_error() throws Exception {
        when(certificateService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/certificates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getCertificateById_error() throws Exception {
        when(certificateService.findAllByBankDetailsId(id)).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/{id}" + "/certificates", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_addCertificate_error() throws Exception {
        mockMvc.perform(post(PATH + "/{id}" + "/certificates", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(certificateService).save(dto);
    }

    @Test
    void test_update_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(certificateService).update(id, dto);

        mockMvc.perform(put(PATH + "/{bank_id}" + "/certificates" + "/?certificate_id=" + id, bank_id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_deleteCertificateById_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(certificateService).deleteByCertificateIdAndBankDetailsId(id, bank_id);

        mockMvc.perform(delete(PATH + "/{bank_id}" + "/certificates" + "/?certificate_id=" + id, bank_id))
                .andExpect(status().isNotFound());
    }
}