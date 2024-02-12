package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.exceptionHandler.exception.NotFoundExceptionEntity;
import com.bank.profile.model.ActualRegistration;
import com.bank.profile.services.interfaces.ActualRegistrationService;
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

@WebMvcTest(ActualRegistrationController.class)
public class ActualRegistrationControllerTest {

    @MockBean
    private ActualRegistrationService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long id = 1L;

    private static ActualRegistrationDto dto = new ActualRegistrationDto();
    private static ActualRegistration model = new ActualRegistration();
    private static final String PATH = "/actualRegistration";

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

