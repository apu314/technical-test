package com.answerdigital.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "colours")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Colours implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Type(type = "org.hibernate.type.BooleanType")
	@Column(name="is_enabled")
	private boolean isEnabled;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                // CascadeType.PERSIST,
                CascadeType.MERGE
                // CascadeType.ALL
            },
            mappedBy = "colours")
	@JsonBackReference
	private Set<People> people = new HashSet<People>();
	
	public Colours() {
	}

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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<People> getPeople() {
		return people;
	}

	public void setPeople(Set<People> people) {
		this.people = people;
	}

}
