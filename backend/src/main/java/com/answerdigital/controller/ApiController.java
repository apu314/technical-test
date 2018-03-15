package com.answerdigital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.answerdigital.dto.PeopleDTO;
import com.answerdigital.entity.People;
import com.answerdigital.service.PeopleService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:8080"})
public class ApiController {
	
	@Autowired
	private PeopleService peopleService;
	
	
	// @RequestMapping(value = "/people", method = RequestMethod.GET)
	@GetMapping(value = "/people/{id}", produces = "application/json")
	public ResponseEntity<People> getPeopleById(@PathVariable("id") String id) {
		People people = peopleService.getPeopleById(Integer.parseInt(id));
		return new ResponseEntity<People>(people, HttpStatus.OK);
	}
	
	@GetMapping(value = "/people", produces = "application/json")
	public ResponseEntity<List<People>> getAllPeople() {
		List<People> list = peopleService.getAllPeople();
		return new ResponseEntity<List<People>>(list, HttpStatus.OK);
	}
	
	// Create New Person
	@PostMapping(value = "/people", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public PeopleDTO createPerson(@Valid @RequestBody PeopleDTO personDTO) {
		peopleService.createPeople(personDTO);
		
		return personDTO;
	}
	
}
