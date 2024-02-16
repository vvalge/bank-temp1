package com.bank.profile.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.bank.profile.model.Profile;
import com.bank.profile.services.interfase.ProfileService;
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
class ProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    void testGetById() throws Exception {
        Long id = 1L;
        Profile profile = new Profile();
        profile.setId(id);
        when(profileService.getById(id)).thenReturn(profile);

        mockMvc.perform(get("/profile/getById/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testCreate() throws Exception {
        Profile profile = new Profile();
        profile.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String profileJson = objectMapper.writeValueAsString(profile);

        mockMvc.perform(post("/profile/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        Profile profile = new Profile();
        profile.setId(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String profileJson = objectMapper.writeValueAsString(profile);

        mockMvc.perform(post("/profile/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Long id = 1L;
        doNothing().when(profileService).deleteById(id);

        mockMvc.perform(delete("/profile/{id}", id))
                .andExpect(status().isOk());
    }
}
