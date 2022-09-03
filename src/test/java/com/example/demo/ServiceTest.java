package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ServiceTest {

    @Mock
    Repo repo;

    @InjectMocks
    Service service;

    @Test
    void shouldReturnSearchItemsContainingDesk() {
        List<Item> mockedList = List.of(
                new Item("Table"),
                new Item("Desk"),
                new Item("Computer Desk"));

        when(repo.findByNameContainingIgnoreCase("desk")).thenReturn(mockedList);

        List<Item> fetchedList = service.searchItems("desk");

        assertEquals(fetchedList, mockedList);
    }

}
