package me.ppangya.wiki.backend.controller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardDTO {

	private @Getter @Setter @NotNull @Size(min = 5, max = 100) String title;
}
