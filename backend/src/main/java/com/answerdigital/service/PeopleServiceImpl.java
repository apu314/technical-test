package com.answerdigital.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.answerdigital.dao.PeopleDAO;
import com.answerdigital.dto.ColoursDTO;
import com.answerdigital.dto.PeopleDTO;
import com.answerdigital.entity.Colours;
import com.answerdigital.entity.People;

@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {
	
	private ModelMapper modelMapper = new ModelMapper();
	
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
	public People createPerson(PeopleDTO personDTO){
		/*
		if (peopleDAO.peopleExists(people.getFirstName(), people.getLastName())) {
			return false;
		} else {
			peopleDAO.createPeople(people);
			return true;
		}
		
		peopleDAO.createPeople(people);
		*/
		People person = modelMapper.map(personDTO, People.class);
		/*
		person.setId((int) 1L);
		person.setFirstName(personDTO.getFirstName());
		person.setLastName(personDTO.getLastName());
		person.setAuthorised(personDTO.isAuthorised());
		person.setEnabled(personDTO.isEnabled());
		person.setValid(personDTO.isValid());
		List<ColoursDTO> colourList = new ArrayList<>();
		colourList.addAll(personDTO.getColours());
		Set<Colours> colours = new HashSet<Colours>();
		// colours.add(colourList);
		//addAll(personDTO.getColours());
		person.setColours(colours);
		*/
		peopleDAO.createPerson(person);
		
		return person;
	}
	
	@Override
	public void updatePerson(PeopleDTO personDTO) {
		People person = modelMapper.map(personDTO, People.class);
		
		peopleDAO.updatePerson(person);
		
	}
	
}
