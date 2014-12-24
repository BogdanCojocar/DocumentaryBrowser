package org.web.data;

import java.util.List;

public interface DocumentaryDAO {
	
	void saveOrUpdate(Documentary doc);
	void delete(int id);
	List<Documentary> getAllDocumentaries();
}
