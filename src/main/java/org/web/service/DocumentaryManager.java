package org.web.service;

import java.util.List;

import org.web.data.Comment;
import org.web.data.Documentary;
import org.web.data.DocumentaryInfo;

public interface DocumentaryManager {

	void saveOrUpdateDocumentary(Documentary doc);
	void saveOrUpdateDocumentaryInfo(DocumentaryInfo docInfo);
	void deleteDocumentary(int id);
	List<Documentary> getAllDocumentaries();
	DocumentaryInfo  getDocumentaryByTitle(String title);
	boolean checkIfDocumentaryInfoIsEmpty(DocumentaryInfo docInfo);
	void addComment(Comment comment, String documentaryTitle);

}
