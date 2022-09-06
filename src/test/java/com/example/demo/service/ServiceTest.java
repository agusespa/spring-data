package com.example.demo.service;

import com.example.demo.dto.response.ItemResponse;
import com.example.demo.entity.Item;
import com.example.demo.repository.Repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    Repo repo;

    @InjectMocks
    Services service;

    @Test
    void shouldReturnSearchItemsContainingDesk() {
        List<Item> mockedList = List.of(
                new Item("Table"),
                new Item("Desk"),
                new Item("Computer Desk"));

        when(repo.findByNameContainingIgnoreCase("desk")).thenReturn(mockedList);

        List<ItemResponse> fetchedList = service.searchItems("desk");

        verify(repo).findByNameContainingIgnoreCase("desk");

        for (int i = 0; i < mockedList.size(); i++) {
            assertEquals(mockedList.get(i).getId(), fetchedList.get(i).getId());
            assertEquals(mockedList.get(i).getName(), fetchedList.get(i).getName());
        }
    }

}
