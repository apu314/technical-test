package com.answerdigital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.answerdigital.dto.PeopleDTO;
import com.answerdigital.entity.Colours;
import com.answerdigital.entity.People;
import com.answerdigital.service.ColoursService;
import com.answerdigital.service.PeopleService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3003"})
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
	// Not used because in the front I have a JSON Object with the complete list of people,
	// and I can access it by the id for example.
	@GetMapping(value = "/person/{id}", produces = "application/json")
	public ResponseEntity<People> getPeopleById(@PathVariable("id") String id) {
		People person = peopleService.getPersonById(Integer.parseInt(id));
		return new ResponseEntity<People>(person, HttpStatus.OK);
	}
	// Receives a people object (a person), saved as PeopleDTO->People (Entity).
	@PostMapping(value = "/person", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PeopleDTO createPerson(@Valid @RequestBody PeopleDTO personDTO) {
		// peopleService.createPerson(personDTO);
		
		if (personDTO.getId() == 0) {
			peopleService.createPerson(personDTO);
		} else {
			peopleService.updatePerson(personDTO);
		}
		
		return personDTO;
	}
	
	@PutMapping(value = "/person", consumes = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public PeopleDTO updatePerson(@Valid @RequestBody PeopleDTO personDTO) {
		peopleService.updatePerson(personDTO);
		
		return personDTO;
	}
	
	@GetMapping(value = "/colours", produces = "application/json")
	public ResponseEntity<List<Colours>> getAllColours() {
		List<Colours> list = coloursService.getAllColours();
		return new ResponseEntity<List<Colours>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/person/{id}/colours/", produces = "application/json")
	public ResponseEntity<List<Colours>> getColoursByPersonId(@PathVariable("id") String id) {
		List<Colours> list = coloursService.getColoursByPersonId(Integer.parseInt(id));
		return new ResponseEntity<List<Colours>>(list, HttpStatus.OK);
	}
}
