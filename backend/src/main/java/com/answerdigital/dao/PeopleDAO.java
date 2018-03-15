package com.answerdigital.dao;

import java.util.List;

import com.answerdigital.entity.People;


public interface PeopleDAO {
	
	public List<People> getAllPeople();
    public People getPeopleById(int id);
    public void createPerson(People people);
    // void updatePeople(People people);
    // void deletePeople(int id);
    boolean peopleExists(String firstName, String lastName);
    // boolean peopleColourExists(int idPeople, int idColour);
	
}
