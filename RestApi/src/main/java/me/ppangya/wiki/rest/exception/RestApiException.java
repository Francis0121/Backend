package me.ppangya.wiki.rest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

public class RestApiException extends ApiException {

	private @Getter HttpStatus httpStatus;
	private @Getter BindingResult bindingResult;

	public RestApiException(HttpStatus httpStatus, BindingResult bindingResult) {
		super(httpStatus, "RestApiException");
		this.httpStatus = httpStatus;
		this.bindingResult = bindingResult;
	}
}
