package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Services services;

    @Test
    void shouldReturnSearchItems() throws Exception {
        when(services.searchItems("desk")).thenReturn(List.of(
                new Item("Desk"),
                new Item("Computer Desk")));

        mockMvc.perform(get("/api/items/search?term=desk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].name").exists())
                .andExpect(jsonPath("$.[1].name").value("Computer Desk"));
    }
}
