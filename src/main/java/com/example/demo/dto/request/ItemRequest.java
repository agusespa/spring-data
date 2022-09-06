package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class ItemRequest {

	@NotBlank
	private String name;

	public ItemRequest() {
	}

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public ItemRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
