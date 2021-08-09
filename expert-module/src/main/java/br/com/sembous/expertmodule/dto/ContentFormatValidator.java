package br.com.sembous.expertmodule.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.sembous.expertmodule.model.contentdefinitions.ContentUtil;

public class ContentFormatValidator implements ConstraintValidator<ContentFormat, JsonNode>{

	@Override
	public boolean isValid(JsonNode value, ConstraintValidatorContext context) {
		if (value == null) {
            return true;
        }		
		return ContentUtil.contentValidator(value);
	}

}
