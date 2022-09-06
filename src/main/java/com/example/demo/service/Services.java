package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.Repo;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class Services {

    Repo repo;

    Logger logger = LoggerFactory.getLogger(Services.class);

    public Services(Repo repo) {
        this.repo = repo;
    }

    public Page<Item> fetchItems(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public List<Item> searchItems(String term) {
        return repo.findByNameContainingIgnoreCase(term);
    }

    public Item createItem(Item item) {
        return repo.save(item);
    }
}
