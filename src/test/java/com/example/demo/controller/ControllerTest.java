package com.example.demo.controller;

import com.example.demo.dto.request.ItemRequest;
import com.example.demo.dto.response.ItemResponse;
import com.example.demo.entity.Item;
import com.example.demo.service.Services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Services services;

    @Test
    void shouldReturnSearchItems() throws Exception {
        when(services.searchItems("desk")).thenReturn(List.of(
                new ItemResponse("b1", "Desk"),
                new ItemResponse("b2", "Computer Desk")));

        mockMvc.perform(get("/api/items/search?term=desk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].name").exists())
                .andExpect(jsonPath("$.[1].name").value("Computer Desk"));
    }

    @Test
    void shouldThrowParamValidationException() throws Exception {
        mockMvc.perform(get("/api/items/search?term="))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowValidationException() throws Exception {
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNewItem() throws Exception {
        when(services.createItem(any(ItemRequest.class))).thenReturn(new ItemResponse("b1", "Desk"));
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Desk\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("b1"))
                .andExpect(jsonPath("$.name").value("Desk"));

        verify(services, times(1)).createItem(any());
    }
}
