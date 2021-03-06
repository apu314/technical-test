package com.answerdigital.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "people")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class People implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name", length = 50)
	private String firstName;
	
	@Column(name="last_name", length = 50)
	private String lastName;
	
	@Type(type = "org.hibernate.type.BooleanType")
	@Column(name="is_authorised")
	private boolean isAuthorised;
	
	@Type(type = "org.hibernate.type.BooleanType")
	@Column(name="is_valid")
	private boolean isValid;
	
	@Type(type = "org.hibernate.type.BooleanType")
	@Column(name="is_enabled")
	private boolean isEnabled;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    // CascadeType.PERSIST,
                    CascadeType.MERGE, // If colour exist don create it, just add that colour to the person
                })
    @JoinTable(name = "colours_people",
            joinColumns = { @JoinColumn(name = "person_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "colour_id", referencedColumnName = "id") })
	//@JsonManagedReference
	//@JsonBackReference
	private List<Colours> colours = new ArrayList<Colours>();

	

	public People() {
	}

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

	public List<Colours> getColours() {
		return colours;
	}

	public void setColours(List<Colours> colours) {
		this.colours = colours;
	}
	
}
