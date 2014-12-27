package org.web.data;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentaryInfoDAOImpl implements DocumentaryInfoDAO {

	private final static String QUERY_BY_TITLE = "from DocumentaryInfo where title = :param";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public DocumentaryInfo getDocumentaryInfoByTitle(String title) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				QUERY_BY_TITLE);
		query.setParameter("param", title);
		return (DocumentaryInfo) query.list().get(0);
	}

	@Override
	public void saveAndUpdate(DocumentaryInfo docInfo) {
		sessionFactory.getCurrentSession().save(docInfo);
	}

}
