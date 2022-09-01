package com.example.demo;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@org.springframework.stereotype.Service
public class Service {

    Repo repo;

    Logger logger = LoggerFactory.getLogger(Service.class);

    public Service(Repo repo) {
        this.repo = repo;
    }

    public Page<Item> fetchItems(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public List<Item> searchItems(String term) {
        return repo.findByNameContainingIgnoreCase(term);
    }

    @Transactional
    public void createItems(int n) {
        for (int i = 1; i < n; i++) {
            repo.save(new Item("newItemsT" + i));
            if (i == 5) throw new RuntimeException();
        }
    }
}
