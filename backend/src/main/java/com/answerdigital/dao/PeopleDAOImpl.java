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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<People> getAllPeople() {
		String hql = "FROM People as p ORDER BY p.id ASC";
		return (List<People>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public People getPeopleById(int id) {
		return entityManager.find(People.class, id);
	}
	
	@Override
	public void createPerson(People person) {
		entityManager.persist(person);
	}
	
	@Override
	public void updatePerson(People person) {
		People savedPerson = entityManager.find(People.class, person.getId());
		entityManager.getTransaction().begin();
		savedPerson.setAuthorised(person.isAuthorised());
		savedPerson.setEnabled(person.isEnabled());
		savedPerson.setValid(person.isValid());
		// Need the list of colours for the person id that i'm updating
		// and the list of colours of the savedPerson.
		// Then check if the list of colours for the updating person is
		// the same as the savedPerson.
		// if colour missing in saved then add it.
		// if colour missing in updating person then remove that colour from
		// the savedPerson colour list.
		
		entityManager.getTransaction().commit();
	}
	
	@Override
	public boolean peopleExists(String firstName, String lastName) {
		String hql = "FROM people as p WHERE p.firstName = ? and p.lastName = ?";
		int count = entityManager.createQuery(hql).setParameter(1, firstName)
		              .setParameter(2, lastName).getResultList().size();
		return count > 0 ? true : false;
	}
	
}
