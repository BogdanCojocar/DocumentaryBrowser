package org.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate4.HibernateObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.data.Documentary;
import org.web.data.DocumentaryDAO;

@Service
@Transactional
@ComponentScan({ "org.web.data" })
public class DocumentaryManagerImpl implements DocumentaryManager {

	private final static Logger log = Logger
			.getLogger(DocumentaryManagerImpl.class.getName());

	@Autowired
	private DocumentaryDAO documentaryDAO;

	public void saveOrUpdate(Documentary doc) {
		documentaryDAO.saveOrUpdate(doc);
	}

	public void delete(int id) {
		try {
			documentaryDAO.delete(id);
		} catch (HibernateObjectRetrievalFailureException e) {
			log.error("Invalid id: ", e);
		}
	}

	public List<Documentary> getAllDocumentaries() {
		return documentaryDAO.getAllDocumentaries();
	}

}
