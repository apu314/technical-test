package com.answerdigital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.answerdigital.dao.PeopleDAO;
import com.answerdigital.dto.PeopleDTO;
import com.answerdigital.entity.People;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {
	
	@Autowired
	private PeopleDAO peopleDAO;
	
	@Override
	public People getPersonById(int id) {
		People obj = peopleDAO.getPeopleById(id);
		return obj;
	}	
	@Override
	public List<People> getAllPeople(){
		return peopleDAO.getAllPeople();
	}
	
	@Override
	public People createPeople(PeopleDTO peopleDTO){
		/*
		if (peopleDAO.peopleExists(people.getFirstName(), people.getLastName())) {
			return false;
		} else {
			peopleDAO.createPeople(people);
			return true;
		}
		
		peopleDAO.createPeople(people);
		*/
		People people = new People();
		
		people.setFirstName(peopleDTO.getFirstName());
		people.setLastName(peopleDTO.getLastName());
		
		peopleDAO.createPerson(people);
		
		return people;
	}
	
}
