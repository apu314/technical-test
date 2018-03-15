package com.answerdigital.dto;

import java.io.Serializable;

import com.answerdigital.entity.Colours;

public class PeopleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private boolean isAuthorised;
	private boolean isValid;
	private boolean isEnabled;
	private Colours colours;
	
	/**
	 * Getters & Setters
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isAuthorised() {
		return isAuthorised;
	}
	public void setAuthorised(boolean isAuthorised) {
		this.isAuthorised = isAuthorised;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Colours getColours() {
		return colours;
	}
	public void setColours(Colours colours) {
		this.colours = colours;
	}
		
}
