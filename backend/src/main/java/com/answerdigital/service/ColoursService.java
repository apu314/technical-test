package com.answerdigital.service;

import java.util.List;

import com.answerdigital.entity.Colours;

public interface ColoursService {
	
	public List<Colours> getAllColours();
	public List<Colours> getColoursByPersonId(int personId);
    
}
