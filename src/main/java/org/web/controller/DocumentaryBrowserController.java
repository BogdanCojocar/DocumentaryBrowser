package org.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.data.Documentary;
import org.web.service.DocumentaryManager;

@Controller
public class DocumentaryBrowserController {

	private final static String HOME_PAGE = "index";
	private final static String HOME_PAGE_REDIRECT = "redirect:/index";
	private final static String DOCUMENTARY = "documentary";
	private final static String DOCUMENTARIES = "documentaries";
	private final static String ADD_DOCUMENTARY = "add";
	private final static String REMOVE_DOCUMENTARY = "remove";

	@Autowired
	private DocumentaryManager manager;

	@RequestMapping(value = HOME_PAGE, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute(DOCUMENTARIES, manager.getAllDocumentaries());
		model.addAttribute(DOCUMENTARY, new Documentary());
		return HOME_PAGE;
	}

	@RequestMapping(value = ADD_DOCUMENTARY, method = RequestMethod.POST)
	public String addDocumentary(@ModelAttribute(DOCUMENTARY) Documentary doc) {
		manager.saveOrUpdate(doc);
		return HOME_PAGE_REDIRECT;
	}

	@RequestMapping(value = REMOVE_DOCUMENTARY, method = RequestMethod.POST)
	public String removeDocumentary(@ModelAttribute(DOCUMENTARY) Documentary doc) {
		manager.delete(doc.getId());
		return HOME_PAGE_REDIRECT;
	}

}
