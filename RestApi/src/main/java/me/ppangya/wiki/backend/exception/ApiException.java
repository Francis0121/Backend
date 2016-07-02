package me.ppangya.wiki.backend.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ApiException extends RuntimeException {

	private @Getter HttpStatus httpStatus;
	private @Getter String message;

}
