package org.web.service;

import java.util.List;

import org.web.data.Documentary;
import org.web.data.DocumentaryInfo;

public interface DocumentaryManager {

	void saveOrUpdateDocumentary(Documentary doc);
	void deleteDocumentary(int id);
	List<Documentary> getAllDocumentaries();
	DocumentaryInfo  getDocumentaryByTitle(String title);
	boolean checkIfDocumentaryIsEmpty(DocumentaryInfo docInfo);

}
