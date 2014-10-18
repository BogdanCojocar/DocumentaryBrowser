package org.web.service;

import java.util.List;

import org.web.data.Documentary;

public interface DocumentaryManager {

	public void saveOrUpdate(Documentary doc);
	public List<Documentary> getAllDocumentaries();

}
