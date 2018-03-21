package com.answerdigital.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = PeopleDTO.class)
public class ColoursDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private Boolean isEnabled;
	private List<PeopleDTO> people;
	
	/**
	 * Getters & Setters
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public List<PeopleDTO> getPeople() {
		return people;
	}
	public void setPeople(List<PeopleDTO> people) {
		this.people = people;
	}
	
	
	
	
}
