package com.answerdigital.dao;

import java.util.List;

import com.answerdigital.entity.Colours;
import com.answerdigital.entity.People;

public interface ColoursDAO {
	
	public List<Colours> getAllColours();
	public List<Colours> getColoursByPersonId(int id);

}
