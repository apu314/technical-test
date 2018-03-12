package com.answerdigital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.answerdigital.entity.People;

@Transactional
@Repository
public class PeopleDAO implements IPeopleDAO {

	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public People getPeopleById(int articleId) {
		return entityManager.find(People.class, articleId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<People> getAllPeople() {
		String hql = "FROM People as p ORDER BY p.id ASC";
		return (List<People>) entityManager.createQuery(hql).getResultList();
	}
	
}
