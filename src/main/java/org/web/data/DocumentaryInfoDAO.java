package org.web.data;

public interface DocumentaryInfoDAO {
	
	DocumentaryInfo getDocumentaryInfoByTitle(String title);
	void saveAndUpdate(DocumentaryInfo docInfo);

}
