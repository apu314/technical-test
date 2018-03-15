package com.answerdigital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.answerdigital.dao.ColoursDAO;
import com.answerdigital.entity.Colours;

@Service
@Transactional
public class ColoursServiceImpl implements ColoursService {
	
	@Autowired
	private ColoursDAO coloursDAO;
		
	@Override
	public List<Colours> getAllColours(){
		return coloursDAO.getAllColours();
	}
		
}
