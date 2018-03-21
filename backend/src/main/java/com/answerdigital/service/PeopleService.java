package com.answerdigital.service;

import java.util.List;

import com.answerdigital.dto.PeopleDTO;
import com.answerdigital.entity.People;

public interface PeopleService {
	
	public List<People> getAllPeople();
	// api test pourposes
    public People getPersonById(int id);
    public People createPerson(PeopleDTO peopleDTO);
    public void updatePerson(PeopleDTO personDTO);
    // public void deletePerson(int id);
    
}
