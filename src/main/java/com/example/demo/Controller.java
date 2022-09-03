package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    Services service;

    public Controller(Services service) {
        this.service = service;
    }

    @GetMapping("/items")
    ResponseEntity<Page<Item>> getItems(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        return ResponseEntity.ok(service.fetchItems(pageable));
    }

    @GetMapping("/items/search")
    ResponseEntity<List<Item>> searchItems(@RequestParam String term) {

        return ResponseEntity.ok(service.searchItems(term));
    }

    @PostMapping("/items")
    void createItems(@RequestParam int n) {
        service.createItems(n);
    }
}
