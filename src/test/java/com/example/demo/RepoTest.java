package com.example.demo;

import com.example.demo.Item;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
    public void shouldReturnItem() {
        List<Item> returned = repo.findByNameContaining("Desk");
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("Desk"));
        expected.add(new Item("Computer Desk"));
        assertEquals(expected, returned);
    }
}
