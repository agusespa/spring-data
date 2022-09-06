package com.example.demo.dto.response;

import javax.validation.constraints.NotBlank;

public class ItemResponse {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

    public ItemResponse() {
    }

    public ItemResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
