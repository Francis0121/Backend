package me.ppangya.wiki.backend.exception;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Response {

	private @Getter @Setter Integer httpStatusCode;
	private @Getter @Setter List<String> messageList;

}
