package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repo extends JpaRepository<Item, Long> {

    List<Item> findByNameContainingIgnoreCase(String term);
}
