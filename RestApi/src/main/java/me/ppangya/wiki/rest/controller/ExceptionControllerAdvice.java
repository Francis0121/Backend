package me.ppangya.wiki.rest.controller;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.rest.exception.Response;
import me.ppangya.wiki.rest.exception.RestApiException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(RestApiException.class)
	public Response restApiExceptionHandler(RestApiException exception) {
		Response response = new Response(exception.getHttpStatus().value(), new ArrayList<>());
		BindingResult bindingResult = exception.getBindingResult();
		if (bindingResult.hasErrors()) {
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			List<String> messageList = fieldErrors.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
			response.setMessageList(messageList);
		}
		log.warn("{}", response, exception);
		return response;
	}
}
