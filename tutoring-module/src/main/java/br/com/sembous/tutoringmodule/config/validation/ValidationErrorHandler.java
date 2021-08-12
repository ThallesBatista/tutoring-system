package br.com.sembous.tutoringmodule.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDto> handle(MethodArgumentNotValidException e) {		
		List<FormErrorDto> errorDtos = new ArrayList<>();
		e.getBindingResult().getFieldErrors().stream().forEach(fieldError -> {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			FormErrorDto formErrorDto = new FormErrorDto(fieldError.getField(), message);
			errorDtos.add(formErrorDto);
		});
		return errorDtos;
	}

}
