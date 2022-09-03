package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

        verify(repo).findByNameContainingIgnoreCase("desk");
        assertEquals(mockedList, fetchedList);
    }

}
