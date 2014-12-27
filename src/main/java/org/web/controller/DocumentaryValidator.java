package org.web.controller;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.web.data.Documentary;
import org.web.data.DocumentaryInfo;

@Component
public class DocumentaryValidator implements Validator {

	private final static int MIN_VALUE = 0;
	private final static int MAX_VALUE = 10;
	private final static String IMAGE_TYPE_PNG = "image/png";
	private final static String IMAGE_TYPE_JPEG = "image/jpeg";

	@Override
	public boolean supports(Class<?> clazz) {
		return Documentary.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof Documentary) {
			checkDocumentary(target, errors);
		} else if (target instanceof DocumentaryInfo) {
			checkDocumentaryInfo(target, errors);
		} else if (target instanceof MultipartFile) {
			checkFile(target, errors);
		}
	}

	private void checkDocumentary(Object target, Errors errors) {
		Documentary doc = (Documentary) target;
		if (doc.getId() <= MIN_VALUE) {
			errors.rejectValue("id", "invalidValue",
					"Documentary id cannot be smaller than 1");
		}
	}

	private void checkDocumentaryInfo(Object target, Errors errors) {
		DocumentaryInfo docInfo = (DocumentaryInfo) target;

		if (docInfo.getRating() <= MIN_VALUE || docInfo.getRating() > MAX_VALUE) {
			errors.rejectValue("rating", "invalidValue",
					"DocumentaryInfo rating has to be between 0 and 10");
		} else {
			UrlValidator urlValidator = new UrlValidator();
			if (!urlValidator.isValid(docInfo.getVideoLink())) {
				errors.rejectValue("videoLink", "invalidLink",
						"DocumentaryInfo video link is invalid");
			}
		}
	}

	private void checkFile(Object target, Errors errors) {
		MultipartFile file = (MultipartFile) target;
		String contentType = file.getContentType();
		if (!contentType.equals(IMAGE_TYPE_JPEG)
				&& !contentType.equals(IMAGE_TYPE_PNG)) {
			errors.rejectValue("imagePath", "invalidImage",
					"Only JPEG/PNG images are suppoted");
		}
	}

}
