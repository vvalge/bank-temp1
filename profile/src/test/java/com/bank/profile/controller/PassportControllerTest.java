package com.bank.profile.controller;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.exceptionHandler.exception.NotFoundExceptionEntity;
import com.bank.profile.model.Passport;

import com.bank.profile.services.interfase.PassportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PassportController.class)
public class PassportControllerTest {

    @MockBean
    private PassportService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long id = 1L;

    private static PassportDto dto = new PassportDto();
    private static Passport model = new Passport();
    private static final String PATH = "/passport";

    static {
        model.setId(id);
    }

    @Test
    public void testGetById_successful() throws Exception {
        when(service.getById(id)).thenReturn(model);

        mockMvc.perform(get(PATH + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    public void testDeleteById_successful() throws Exception {
        mockMvc.perform(delete(PATH + "/{id}", id))
                .andExpect(status().isOk());

        verify(service).deleteById(id);
    }

    @Test
    public void testCreate_successful() throws Exception {

        mockMvc.perform(post(PATH + "/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).create(dto);
    }

    @Test
    public void testGetById_error() throws Exception {
        when(service.getById(id)).thenReturn(model);

        mockMvc.perform(get(PATH + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id.intValue())));
    }

    @Test
    public void testUpdate_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundExceptionEntity(errorMessage)).when(service).update(id, dto);

        mockMvc.perform(post(PATH + "/update/{id}", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteById_error() throws Exception {
        String errorMessage = "Entity not found";

        doThrow(new NotFoundExceptionEntity(errorMessage)).when(service).deleteById(id);

        mockMvc.perform(delete(PATH + "/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreate_error() throws Exception {
        mockMvc.perform(post(PATH + "/create")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).create(dto);
    }


    @Test
    public void testUpdate_successful() throws Exception {

        mockMvc.perform(post(PATH + "/update/{id}", id)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).update(id, dto);
    }
}