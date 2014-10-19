package org.web.data;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentaryDAOImpl implements DocumentaryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdate(Documentary doc) {
		sessionFactory.getCurrentSession().save(doc);
	}

	public void delete(int id) {
		Documentary doc = (Documentary) sessionFactory.getCurrentSession()
				.load(Documentary.class, id);

		if (doc != null) {
			sessionFactory.getCurrentSession().delete(doc);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Documentary> getAllDocumentaries() {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Documentary").list();
	}
}
