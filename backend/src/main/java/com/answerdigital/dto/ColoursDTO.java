package com.answerdigital.dto;

import java.io.Serializable;

import com.answerdigital.entity.People;

public class ColoursDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private Boolean isEnabled;
	private People people;
	
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
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	
	
	
	
}
