package com.search.microservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="accommodation_category")
public class AccommodationCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String description;
    
	public AccommodationCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccommodationCategory(String description) {
		super();
		this.description = description;
	}

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
	}
}
