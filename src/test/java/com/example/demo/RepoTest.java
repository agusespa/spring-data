package com.example.demo;

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
        repo.save(new Item("Table"));
        repo.save(new Item("Desk"));
        repo.save(new Item("Computer Desk"));
    }

    @Test
    public void shouldReturnItemsContainingDesk() {
        List<Item> returned = repo.findByNameContaining("Desk");
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("Desk"));
        expected.add(new Item("Computer Desk"));
        assertEquals(expected.get(0).getName(), returned.get(0).getName());
        assertEquals(expected.get(1).getName(), returned.get(1).getName());
    }
}
