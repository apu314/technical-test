package com.answerdigital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.answerdigital.entity.Colours;
import com.answerdigital.entity.People;

@Transactional
@Repository
public class ColoursDAOImpl implements ColoursDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Colours> getAllColours() {
		String hql = "FROM Colours as c ORDER BY c.id ASC";
		return (List<Colours>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public List<Colours> getColoursByPersonId(int personId) {
		// String hql = "FROM Colours as c JOIN c.people p Where p.id=:personId";
		TypedQuery<Colours> query = entityManager.createQuery(
			    "SELECT DISTINCT c FROM People p LEFT JOIN p.colours c " +
			    "WHERE p = :personId", Colours.class);
			query.setParameter("personId", personId);
		return (List<Colours>) query.getResultList();
	}

}
