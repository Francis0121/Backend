package me.ppangya.wiki.backend.exception;

import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

	private @Getter String message;

	public ResourceNotFoundException() {
		super(HttpStatus.NOT_FOUND, "ResourceNotFoundException");
		this.message = "";
	}

	public ResourceNotFoundException(String format, Object... appendMessages) {
		super(HttpStatus.NOT_FOUND, "ResourceNotFoundException");
		this.message = MessageFormatter.arrayFormat(format, appendMessages).getMessage();
	}
}
