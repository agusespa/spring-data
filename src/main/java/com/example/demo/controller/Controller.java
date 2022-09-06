package com.example.demo.controller;

import com.example.demo.dto.request.ItemRequest;
import com.example.demo.dto.response.ItemResponse;
import com.example.demo.service.Services;
import com.example.demo.entity.Item;
import com.example.demo.service.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class Controller {

    Services service;

    public Controller(Services service) {
        this.service = service;
    }

    @GetMapping("/items")
    ResponseEntity<List<ItemResponse>> getItems(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        return ResponseEntity.ok(service.fetchItems(pageable));
    }

    @GetMapping("/items/search")
    ResponseEntity<List<ItemResponse>> searchItems(@RequestParam @NotBlank String term) {
        return ResponseEntity.ok(service.searchItems(term));
    }

    @PostMapping("/items")
    ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemRequest item) {
        System.out.println("reached controoler");
        ItemResponse savedItem = service.createItem(item);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedItem);
    }
}
