package com.answerdigital.dao;

import java.util.List;

import com.answerdigital.entity.People;


public interface IPeopleDAO {
	
	public List<People> getAllPeople();
    public People getPeopleById(int id);
    // void createPeople(People people);
    // void updatePeople(People people);
    // void deletePeople(int id);
    // boolean peopleExists(String name, String colour);
	
}
