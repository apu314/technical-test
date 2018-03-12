package com.answerdigital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.answerdigital.dao.IPeopleDAO;
import com.answerdigital.entity.People;

@Service
public class PeopleService implements IPeopleService {
	
	@Autowired
	private IPeopleDAO peopleDAO;
	
	@Override
	public People getPeopleById(int id) {
		People obj = peopleDAO.getPeopleById(id);
		return obj;
	}	
	@Override
	public List<People> getAllPeople(){
		return peopleDAO.getAllPeople();
	}
	

}
