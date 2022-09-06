package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepoTest {

    @Autowired
    Repo repo;

    @BeforeAll
    void setUp() {
        repo.saveAllAndFlush(List.of(
        new Item("Table"),
        new Item("Desk"),
        new Item("Computer Desk")));
    }

    @Test
    public void shouldRetrieveTwoItemsContainingDesk() {
        List<Item> returned = repo.findByNameContainingIgnoreCase("desk");
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("Desk"));
        expected.add(new Item("Computer Desk"));
        assertEquals(2, returned.size());
        assertEquals(expected.get(0).getName(), returned.get(0).getName());
        assertEquals(expected.get(1).getName(), returned.get(1).getName());
    }

    @Test
    public void shouldReturn0ItemsContainingChair() {
        List<Item> returned = repo.findByNameContainingIgnoreCase("chair");
        assertTrue(returned.isEmpty());
    }

}
