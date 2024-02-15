package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.BranchEntity;
import com.bank.publicinfo.exception.NotFoundException;
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

@WebMvcTest(BranchController.class)
class BranchControllerTest {

    private static final String PATH = "/branches";
    private static final Long id = 1L;
    private static final Long phoneNumber = 2L;
    private static final BranchDto dto = new BranchDto();
    private static final BranchEntity entity = new BranchEntity();

    static {
        entity.setId(id);
        dto.setId(id);
        entity.setPhoneNumber(phoneNumber);
        dto.setPhoneNumber(phoneNumber);
    }

    @MockBean
    private BranchService branchService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_getAll_successful() throws Exception {
        when(branchService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getById_successful() throws Exception {
        when(branchService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_getByPhoneNumber_successful() throws Exception {
        when(branchService.findByPhoneNumber(phoneNumber)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?phone_number=" + phoneNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.phoneNumber", is(phoneNumber.intValue())));
    }

    @Test
    void test_createBranch_successful() throws Exception {
        mockMvc.perform(post(PATH)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(branchService).save(dto);
    }

    @Test
    void test_update_successful() throws Exception {
        mockMvc.perform(put(PATH + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(branchService).update(id, dto);
    }

    @Test
    void test_deleteById_successful() throws Exception {
        mockMvc.perform(delete(PATH + "/?id=" + id))
                .andExpect(status().isNoContent());

        verify(branchService).deleteById(id);
    }

    @Test
    void test_getAll_error() throws Exception {
        when(branchService.findAll()).thenReturn(List.of(dto, dto));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void test_getById_error() throws Exception {
        when(branchService.findById(id)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    void test_getByPhoneNumber_error() throws Exception {
        when(branchService.findByPhoneNumber(phoneNumber)).thenReturn(dto);

        mockMvc.perform(get(PATH + "/?phone_number=" + phoneNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.phoneNumber", is(phoneNumber.intValue())));
    }

    @Test
    void test_createBranch_error() throws Exception {
        mockMvc.perform(post(PATH)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(branchService).save(dto);
    }

    @Test
    void test_update_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(branchService).update(id, dto);

        mockMvc.perform(put(PATH + "/?id=" + id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_deleteById_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundException(errorMessage)).when(branchService).deleteById(id);

        mockMvc.perform(delete(PATH + "/?id=" + id))
                .andExpect(status().isNotFound());
    }
}