package org.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.data.Documentary;
import org.web.data.DocumentaryDAO;

@Service
@Transactional
@ComponentScan({ "org.web.data" })
public class DocumentaryManagerImpl implements DocumentaryManager {

	@Autowired
	private DocumentaryDAO documentaryDAO;

	public void saveOrUpdate(Documentary doc) {
		documentaryDAO.saveOrUpdate(doc);
	}

	public List<Documentary> getAllDocumentaries() {
		return documentaryDAO.getAllDocumentaries();
	}

}
