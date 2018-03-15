package com.answerdigital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.answerdigital.entity.Colours;

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

}
