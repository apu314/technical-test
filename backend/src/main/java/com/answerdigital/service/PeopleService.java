package com.answerdigital.service;

import java.util.List;

import com.answerdigital.dto.PeopleDTO;
import com.answerdigital.entity.People;

public interface PeopleService {
	
	public List<People> getAllPeople();
    public People getPersonById(int id);
    public People createPeople(PeopleDTO peopleDTO);
    // void updatePeople(People people);
    // void deletePeople(int id);
    
}
