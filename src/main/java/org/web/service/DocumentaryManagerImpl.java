package org.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate4.HibernateObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.data.Comment;
import org.web.data.Documentary;
import org.web.data.DocumentaryDAO;
import org.web.data.DocumentaryInfo;
import org.web.data.DocumentaryInfoDAO;

@Service
@Transactional
@ComponentScan({ "org.web.data" })
public class DocumentaryManagerImpl implements DocumentaryManager {

	private final static Logger log = Logger
			.getLogger(DocumentaryManagerImpl.class.getName());

	@Autowired
	private DocumentaryDAO documentaryDAO;

	@Autowired
	private DocumentaryInfoDAO documentaryInfoDAO;

	@Override
	public void saveOrUpdateDocumentary(Documentary doc) {
		documentaryDAO.saveOrUpdate(doc);
	}

	@Override
	public void saveOrUpdateDocumentaryInfo(DocumentaryInfo docInfo) {
		documentaryInfoDAO.saveAndUpdate(docInfo);
	}

	@Override
	public void deleteDocumentary(int id) {
		try {
			documentaryDAO.delete(id);
		} catch (HibernateObjectRetrievalFailureException e) {
			log.error("Invalid id: ", e);
		}
	}

	@Override
	public List<Documentary> getAllDocumentaries() {
		try {
			return documentaryDAO.getAllDocumentaries();
		} catch (HibernateObjectRetrievalFailureException e) {
			log.error("Invalid data: ", e);
			return new ArrayList<Documentary>();
		}
	}

	@Override
	public DocumentaryInfo getDocumentaryByTitle(String title) {
		try {
			return documentaryInfoDAO.getDocumentaryInfoByTitle(title);
		} catch (IndexOutOfBoundsException e) {
			log.error("Title not found");
			return new DocumentaryInfo();
		}
	}
	
	@Override
	public void addComment(Comment comment, String documentaryTitle) {
		DocumentaryInfo docInfo = getDocumentaryByTitle(documentaryTitle);
		List<Comment> comments = docInfo.getComments();
		comments.add(comment);
		docInfo.setComments(comments);
		saveOrUpdateDocumentaryInfo(docInfo);
	}

	@Override
	public boolean checkIfDocumentaryInfoIsEmpty(DocumentaryInfo docInfo) {
		boolean isEmpty = true;

		if (docInfo.getTitle() != null || docInfo.getDescription() != null
				|| docInfo.getImagePath() != null
				|| docInfo.getVideoLink() != null || docInfo.getRating() != 0) {
			isEmpty = false;
		}
		return isEmpty;
	}
}
