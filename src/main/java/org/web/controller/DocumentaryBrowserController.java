package org.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	private final static String DOCUMENTARY_INFO = "documentaryInfo";
	private final static String DOCUMENTARY_TITLE = "title";
	private final static String DOCUMENTARY_IMG_PATH = "imgpath";
	private final static String DOCUMENTARY_DESCRIPTION = "description";
	private final static String DOCUMENTARY_VIDEO_LINK = "videolink";
	private final static String DOCUMENTARY_RATING = "rating";
	private final static String DOCUMENTARIES = "documentaries";
	private final static String ADD_DOCUMENTARY = "add";
	private final static String ADD_DOCUMENTARY_INFO = "add_info";
	private final static String REMOVE_DOCUMENTARY = "remove";
	private final static String MESSAGE = "message";
	private final static String IMAGE_FOLDER = "/resources/images/";

	@Autowired
	private DocumentaryManager manager;

	@Autowired
	private DocumentaryValidator documentaryValidator;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = { "/", HOME_PAGE }, method = RequestMethod.GET)
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
			return sendErrorMessage(result.getFieldError().getDefaultMessage(),
					model);
		}

		manager.saveOrUpdateDocumentary(doc);

		model.addAttribute(DOCUMENTARY_INFO, new DocumentaryInfo());
		model.addAttribute(DOCUMENTARY_TITLE, doc.getName());
		return DOCUMENTARY_INFO;
	}

	@RequestMapping(value = ADD_DOCUMENTARY_INFO, method = RequestMethod.POST)
	public String postDocumentaryInfo(Model model,
			@Valid @ModelAttribute(DOCUMENTARY_INFO) DocumentaryInfo docInfo,
			@RequestParam(value = "image", required = true) MultipartFile file,
			final BindingResult result) {

		documentaryValidator.validate(file, result);
		if (result.hasErrors()) {
			return sendErrorMessage(result.getFieldError().getDefaultMessage(),
					model);
		}

		String filePath = createFileUploadPath(file.getOriginalFilename());
		String errorMessage = uploadFile(file, filePath);

		if (!errorMessage.isEmpty()) {
			return sendErrorMessage(errorMessage, model);
		} else {
			docInfo.setImagePath(IMAGE_FOLDER + file.getOriginalFilename());
		}

		documentaryValidator.validate(docInfo, result);
		if (result.hasErrors()) {
			return sendErrorMessage(result.getFieldError().getDefaultMessage(),
					model);
		}

		log.info("saving documentary info: " + docInfo.getTitle());
		manager.saveOrUpdateDocumentaryInfo(docInfo);
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
			return sendErrorMessage(result.getFieldError().getDefaultMessage(),
					model);
		}

		manager.deleteDocumentary(doc.getId());
		return HOME_PAGE_REDIRECT;
	}

	@RequestMapping(value = DOCUMENTARY_PAGE, method = RequestMethod.GET)
	public String showDocumentary(Model model, @PathVariable String name) {

		DocumentaryInfo docInfo = manager.getDocumentaryByTitle(name);

		if (manager.checkIfDocumentaryInfoIsEmpty(docInfo)) {
			return sendErrorMessage("Documentary is not in the list", model);
		}

		log.info("Showing info for documentary: " + docInfo.getTitle());

		model.addAttribute(DOCUMENTARY_TITLE, docInfo.getTitle());
		model.addAttribute(DOCUMENTARY_IMG_PATH, docInfo.getImagePath());
		model.addAttribute(DOCUMENTARY_DESCRIPTION, docInfo.getDescription());
		model.addAttribute(DOCUMENTARY_VIDEO_LINK, docInfo.getVideoLink());
		model.addAttribute(DOCUMENTARY_RATING, docInfo.getRating());
		return DOCUMENTARY;
	}

	private String createFileUploadPath(String originalFileName) {
		String webappRoot = servletContext.getRealPath("/");
		StringBuilder filePathBuilder = new StringBuilder(webappRoot);
		filePathBuilder.append(IMAGE_FOLDER);
		filePathBuilder.append(originalFileName);
		return filePathBuilder.toString();
	}

	private String uploadFile(MultipartFile file, String filePath) {
		String errorMessage = "";

		if (!file.isEmpty()) {
			File storedFile = new File(filePath);
			if (storedFile.exists() && !storedFile.isDirectory()) {
				log.info("File " + filePath + "is already uploaded");
			} else {
				try {
					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(filePath)));
					stream.write(bytes);
					stream.close();
					log.info("Successfully uploaded " + filePath);
				} catch (Exception e) {
					errorMessage = "Failed to upload " + filePath + ": "
							+ e.getMessage();
				}
			}
		} else {
			errorMessage = "File is empty";
		}
		return errorMessage;
	}

	private String sendErrorMessage(String errorMessage, Model model) {
		log.error(errorMessage);
		model.addAttribute(MESSAGE, errorMessage);
		return ERROR_PAGE;
	}
}
