package org.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.web.data.Documentary;
import org.web.data.DocumentaryInfo;
import org.web.service.DocumentaryManager;

@Controller
public class DocumentaryBrowserController {

	private final static Logger log = Logger
			.getLogger(DocumentaryBrowserController.class.getName());

	private final static String HOME_PAGE = "index";
	private final static String HOME_PAGE_REDIRECT = "redirect:/index";
	private final static String ERROR_PAGE = "error";
	private final static String DOCUMENTARY_PAGE = "documentary/{name}";
	private final static String DOCUMENTARY = "documentary";
	private final static String DOCUMENTARY_TITLE = "title";
	private final static String DOCUMENTARY_IMG_PATH = "imgpath";
	private final static String DOCUMENTARY_DESCRIPTION = "description";
	private final static String DOCUMENTARY_VIDEO_LINK = "videolink";
	private final static String DOCUMENTARY_RATING = "rating";
	private final static String DOCUMENTARIES = "documentaries";
	private final static String ADD_DOCUMENTARY = "add";
	private final static String REMOVE_DOCUMENTARY = "remove";
	private final static String MESSAGE = "message";

	@Autowired
	private DocumentaryManager manager;

	@Autowired
	private DocumentaryValidator documentaryValidator;

	@RequestMapping(value = HOME_PAGE, method = RequestMethod.GET)
	public String index(Model model) {

		log.info("Accessing index method.");

		List<Documentary> documentaries = manager.getAllDocumentaries();
		model.addAttribute(DOCUMENTARIES, documentaries);
		log.debug("Documentaries: " + documentaries.toString());

		model.addAttribute(DOCUMENTARY, new Documentary());

		return HOME_PAGE;
	}

	@RequestMapping(value = ADD_DOCUMENTARY, method = RequestMethod.POST)
	public String addDocumentary(Model model,
			@Valid @ModelAttribute(DOCUMENTARY) Documentary doc,
			final BindingResult result) {

		log.info("Accessing addDocumentary method. Documentary object is: "
				+ doc.toString());

		if (result.hasErrors()) {
			String errorMessage = result.getFieldError().getDefaultMessage();
			log.error(errorMessage);
			model.addAttribute(MESSAGE, errorMessage);
			return ERROR_PAGE;
		}

		manager.saveOrUpdateDocumentary(doc);
		return HOME_PAGE_REDIRECT;
	}

	@RequestMapping(value = REMOVE_DOCUMENTARY, method = RequestMethod.POST)
	public String removeDocumentary(Model model,
			@ModelAttribute(DOCUMENTARY) Documentary doc,
			final BindingResult result) {

		log.info("Accessing removeDocumentary method. Documentary object is: "
				+ doc.toString());

		documentaryValidator.validate(doc, result);
		if (result.hasErrors()) {
			String errorMessage = result.getFieldError().getDefaultMessage();
			log.error(errorMessage);
			model.addAttribute(MESSAGE, errorMessage);
			return ERROR_PAGE;
		}

		manager.deleteDocumentary(doc.getId());
		return HOME_PAGE_REDIRECT;
	}

	@RequestMapping(value = DOCUMENTARY_PAGE, method = RequestMethod.GET)
	public String showDocumentary(Model model, @PathVariable String name) {

		DocumentaryInfo docInfo = manager.getDocumentaryByTitle(name);

		if (manager.checkIfDocumentaryIsEmpty(docInfo)) {
			model.addAttribute(MESSAGE, "Documentary is not in the list");
			return ERROR_PAGE;
		}

		log.info("Showing info for documentary: " + docInfo.toString());

		model.addAttribute(DOCUMENTARY_TITLE, docInfo.getTitle());
		model.addAttribute(DOCUMENTARY_IMG_PATH, docInfo.getImagePath());
		model.addAttribute(DOCUMENTARY_DESCRIPTION, docInfo.getDescription());
		model.addAttribute(DOCUMENTARY_VIDEO_LINK, docInfo.getVideoLink());
		model.addAttribute(DOCUMENTARY_RATING, docInfo.getRating());
		return DOCUMENTARY;
	}

}
