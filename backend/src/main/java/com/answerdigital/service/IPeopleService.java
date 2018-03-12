package com.answerdigital.service;

import java.util.List;

import com.answerdigital.entity.People;

public interface IPeopleService {
	
	List<People> getAllPeople();
    People getPeopleById(int id);
    // boolean createPeople(People people);
    // void updatePeople(People people);
    // void deletePeople(int id);
    
}
