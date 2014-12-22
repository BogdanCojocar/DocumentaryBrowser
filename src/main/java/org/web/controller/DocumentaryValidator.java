package org.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.web.data.Documentary;

@Component
public class DocumentaryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Documentary.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Documentary doc = (Documentary) target;
		if (doc.getId() < 0) {
			errors.rejectValue("id", "negativeValue",
					"Documentary id cannot be negative");
		}
	}

}
