package com.answerdigital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.answerdigital.entity.People;
import com.answerdigital.service.IPeopleService;

@Controller
@RequestMapping("/api")
//@CrossOrigin(origins = {"http://localhost:4200"})
public class ApiController {
	
	@Autowired
	private IPeopleService peopleService;
	
	
	// @RequestMapping(value = "/people", method = RequestMethod.GET)
	@GetMapping(value = "/people/{id}", produces = "application/json")
	public ResponseEntity<People> getArticleById(@PathVariable("id") String id) {
		People people = peopleService.getPeopleById(Integer.parseInt(id));
		return new ResponseEntity<People>(people, HttpStatus.OK);
	}
	
	@GetMapping(value = "/people", produces = "application/json")
	public ResponseEntity<List<People>> getAllPeople() {
		List<People> list = peopleService.getAllPeople();
		return new ResponseEntity<List<People>>(list, HttpStatus.OK);
	}
	
	
}
