package org.web.data;

import java.util.List;

public interface DocumentaryDAO {
	
	public void saveOrUpdate(Documentary doc);
	public void delete(int id);
	public List<Documentary> getAllDocumentaries();
}
