package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.LicenseEntity;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.service.interfaces.BankDetailsService;
import com.bank.publicinfo.service.interfaces.LicenseService;
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

@WebMvcTest(LicenseController.class)
class LicenseControllerTest {

    private static final String PATH = "/bank-details";
    private static final Long id = 1L;
    private static final Long bank_id = 2L;
    private static final LicenseDto dto = new LicenseDto();
    private static final LicenseEntity entity = new LicenseEntity();

    static {
        entity.setId(id);
        dto.setId(id);
    }

    @MockBean
    private LicenseService licenseService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BankDetailsService bankDetailsService;

    @Test
    void test_getAllLicenses_successful() throws Exception {
        when(licenseService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/licenses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getLicenseById_successful() throws Exception {
        when(licenseService.findAllByBankDetailsId(id)).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/{id}" + "/licenses", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_addLicense_successful() throws Exception {
        mockMvc.perform(post(PATH + "/{id}" + "/licenses", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(licenseService).save(dto);
    }

    @Test
    void test_update_successful() throws Exception {
        mockMvc.perform(put(PATH + "/{bank_id}" + "/licenses" + "/?license_id=" + id, bank_id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(licenseService).update(id, dto);
    }

    @Test
    void test_deleteLicenseById_successful() throws Exception {
        mockMvc.perform(delete(PATH + "/{bank_id}" + "/licenses" + "/?license_id=" + id, bank_id))
                .andExpect(status().isNoContent());

        verify(licenseService).deleteByLicenseIdAndBankDetailsId(id, bank_id);
    }

    @Test
    void test_getAllLicenses_error() throws Exception {
        when(licenseService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/licenses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getLicenseById_error() throws Exception {
        when(licenseService.findAllByBankDetailsId(id)).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/{id}" + "/licenses", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_addLicense_error() throws Exception {
        mockMvc.perform(post(PATH + "/{id}" + "/licenses", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(licenseService).save(dto);
    }

    @Test
    void test_update_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(licenseService).update(id, dto);

        mockMvc.perform(put(PATH + "/{bank_id}" + "/licenses" + "/?license_id=" + id, bank_id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_deleteLicenseById_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(licenseService).deleteByLicenseIdAndBankDetailsId(id, bank_id);

        mockMvc.perform(delete(PATH + "/{bank_id}" + "/licenses" + "/?license_id=" + id, bank_id))
                .andExpect(status().isNotFound());
    }
}