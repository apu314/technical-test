package com.answerdigital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.answerdigital.dto.PeopleDTO;
import com.answerdigital.entity.Colours;
import com.answerdigital.entity.People;
import com.answerdigital.service.ColoursService;
import com.answerdigital.service.PeopleService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:8080"})
public class ApiController {
	
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private ColoursService coloursService;
	
	
	@GetMapping(value = "/people", produces = "application/json")
	public ResponseEntity<List<People>> getAllPeople() {
		List<People> list = peopleService.getAllPeople();
		return new ResponseEntity<List<People>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/person/{id}", produces = "application/json")
	public ResponseEntity<People> getPeopleById(@PathVariable("id") String id) {
		People person = peopleService.getPersonById(Integer.parseInt(id));
		return new ResponseEntity<People>(person, HttpStatus.OK);
	}
	
	@PostMapping(value = "/person", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public PeopleDTO createPerson(@Valid @RequestBody PeopleDTO personDTO) {
		peopleService.createPeople(personDTO);
		
		return personDTO;
	}
	
	@PostMapping(value = "/person/addColour", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public PeopleDTO addRemovePersonColor(@Valid @RequestBody PeopleDTO personDTO) {
		
		peopleService.createPeople(personDTO);
		
		return personDTO;
	}
	
	@GetMapping(value = "/colours", produces = "application/json")
	public ResponseEntity<List<Colours>> getAllColours() {
		List<Colours> list = coloursService.getAllColours();
		return new ResponseEntity<List<Colours>>(list, HttpStatus.OK);
	}
	
}
