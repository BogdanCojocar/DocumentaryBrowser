package org.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.web.data.Documentary;
import org.web.service.DocumentaryManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope("prototype")
@ComponentScan({ "org.web.service" })
public class EditDocumentariesAction extends ActionSupport implements
		Preparable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(EditDocumentariesAction.class);

	@Autowired
	private DocumentaryManager documentaryManager;

	private Documentary documentary;
	private List<Documentary> documentaries;

	public String listDocumentaries() {
		LOG.info("Listing the documentaries..");
		documentaries = documentaryManager.getAllDocumentaries();
		return SUCCESS;
	}

	public String addDocumentary() {
		LOG.info("Saving documentary: " + documentary);
		documentaryManager.saveOrUpdate(documentary);
		return SUCCESS;
	}

	public void prepare() throws Exception {
	}

	public Documentary getDocumentary() {
		return documentary;
	}

	public void setDocumentary(Documentary documentary) {
		this.documentary = documentary;
	}

	public List<Documentary> getDocumentaries() {
		return documentaries;
	}

	public void setDocumentaries(List<Documentary> documentaries) {
		this.documentaries = documentaries;
	}

}
