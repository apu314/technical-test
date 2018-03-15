package com.answerdigital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.answerdigital.entity.People;

@Transactional
@Repository
public class PeopleDAOImpl implements PeopleDAO {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public People getPeopleById(int id) {
		return entityManager.find(People.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<People> getAllPeople() {
		String hql = "FROM People as p ORDER BY p.id ASC";
		return (List<People>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public void createPerson(People person) {
		entityManager.persist(person);
	}
	
	@Override
	public boolean peopleExists(String firstName, String lastName) {
		String hql = "FROM people as p WHERE p.firstName = ? and p.lastName = ?";
		int count = entityManager.createQuery(hql).setParameter(1, firstName)
		              .setParameter(2, lastName).getResultList().size();
		return count > 0 ? true : false;
	}
	
}
