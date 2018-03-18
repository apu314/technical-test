package com.answerdigital.dao;

import java.util.List;

import com.answerdigital.entity.People;


public interface PeopleDAO {
	
	public List<People> getAllPeople();
    public People getPeopleById(int id);
    public void createPerson(People people);
    
    // le tengo que pasar el objeto persona con la lista de colores seteada
    // void deletePeople(int id);
    boolean peopleExists(String firstName, String lastName);
	void updatePerson(People person);
    
    //boolean personColourExist(int personId, int colourId);
	
}
