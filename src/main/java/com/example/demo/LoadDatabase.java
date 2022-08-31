package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(Repo repository) {

        return args -> {
            for (int n = 1; n < 100; n++) {
                repository.save(new Item("Item" + "shit" + n));
            }
        };
    }
}
