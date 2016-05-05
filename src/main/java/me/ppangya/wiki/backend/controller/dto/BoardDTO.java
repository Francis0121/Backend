package me.ppangya.wiki.backend.controller.dto;

import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardDTO {

	private @Getter @Setter String title;
}
