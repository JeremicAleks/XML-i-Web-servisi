package com.centralapi.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccommodationDTO {
	
	@NotNull(message = "Id is required")
	private Long id;
	
	@NotNull(message = "Description is required!")
	String description;
	
	public AccommodationDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		System.out.println("jebo ti ja mater");
	}
}
