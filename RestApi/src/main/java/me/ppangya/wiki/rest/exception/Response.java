package me.ppangya.wiki.rest.exception;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Response {

	private @Getter @Setter Integer httpStatusCode;
	private @Getter @Setter List<String> messageList;

}
