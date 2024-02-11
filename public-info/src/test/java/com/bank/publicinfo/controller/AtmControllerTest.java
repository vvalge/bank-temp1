package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.AtmEntity;
import com.bank.publicinfo.exception.BadRequestException;
import com.bank.publicinfo.exception.NotFoundException;
import com.bank.publicinfo.service.interfaces.AtmService;
import com.bank.publicinfo.service.interfaces.BranchService;
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

@WebMvcTest(AtmController.class)
class AtmControllerTest {

    private static final String PATH = "/branches";
    private static final Long id = 1L;
    private static final AtmDto dto = new AtmDto();
    private static final AtmEntity entity = new AtmEntity();

    static {
        entity.setId(id);
        dto.setId(id);
    }

    @MockBean
    private AtmService atmService;
    @MockBean
    private BranchService branchService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_getAllAtms_successful() throws Exception {
        when(atmService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/atms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getAtmById_successful() throws Exception {
        when(atmService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/atms" + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_getAtmByBranchId_successful() throws Exception {
        when(atmService.findAllByBranchId(id)).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/{id}" + "/atms", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_addAtmWithoutBranch_successful() throws Exception {
        mockMvc.perform(post(PATH + "/atms")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(atmService).save(dto);
    }

    @Test
    void test_addAtm_successful() throws Exception {
        mockMvc.perform(post(PATH + "/{id}" + "/atms", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(atmService).save(dto);
    }

    @Test
    void test_update_successful() throws Exception {
        mockMvc.perform(put(PATH + "/atms" + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(atmService).update(id, dto);
    }

    @Test
    void test_deleteAtmById_successful() throws Exception {
        mockMvc.perform(delete(PATH + "/atms" + "/?id=" + id))
                .andExpect(status().isNoContent());

        verify(atmService).deleteById(id);
    }

    @Test
    void test_getAllAtms_error() throws Exception {
        when(atmService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/atms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getAtmById_error() throws Exception {
        when(atmService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/atms" + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_getAtmByBranchId_error() throws Exception {
        when(atmService.findAllByBranchId(id)).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH + "/{id}" + "/atms", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_addAtmWithoutBranch_error() throws Exception {
        mockMvc.perform(post(PATH + "/atms")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(atmService).save(dto);
    }

    @Test
    void test_addAtm_error() throws Exception {
        mockMvc.perform(post(PATH + "/{id}" + "/atms", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(atmService).save(dto);
    }

    @Test
    void test_update_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(atmService).update(id, dto);

        mockMvc.perform(put(PATH + "/atms" + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_deleteAtmById_errorNotFound() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(atmService).deleteById(id);

        mockMvc.perform(delete(PATH + "/atms" + "/?id=" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_deleteAtmById_errorBadRequest() throws Exception {
        String errorMessage = "Bad Request";

        doThrow(new BadRequestException(errorMessage)).when(atmService).deleteById(id);

        mockMvc.perform(delete(PATH + "/atms" + "/?id=" + id))
                .andExpect(status().isNotFound());
    }
}