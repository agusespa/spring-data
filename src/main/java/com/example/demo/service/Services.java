package com.example.demo.service;

import com.example.demo.dto.request.ItemRequest;
import com.example.demo.dto.response.ItemResponse;
import com.example.demo.entity.Item;
import com.example.demo.repository.Repo;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Services {

    Repo repo;

    Logger logger = LoggerFactory.getLogger(Services.class);

    public Services(Repo repo) {
        this.repo = repo;
    }

    public List<ItemResponse> fetchItems(Pageable pageable) {
        Page<Item> page = repo.findAll(pageable);
        List<Item> itemList = new ArrayList<>();
        if(page.hasContent()) {
            itemList = page.getContent();
        }

        List<ItemResponse> responseList = new ArrayList<>();
        for (Item item : itemList) {
            responseList.add(new ItemResponse(item.getId(), item.getName()));
        }
        return responseList;
    }

    public List<ItemResponse> searchItems(String term) {
        List<Item> itemList = repo.findByNameContainingIgnoreCase(term);
        List<ItemResponse> responseList = new ArrayList<>();
        for (Item item : itemList) {
            responseList.add(new ItemResponse(item.getId(), item.getName()));
        }
        return responseList;
    }

    public ItemResponse createItem(ItemRequest item) {
        System.out.println("reached service");
        Item newItem = new Item(item.getName());
        Item savedItem = repo.save(newItem);
        return new ItemResponse(savedItem.getId(), savedItem.getName());
    }
}
