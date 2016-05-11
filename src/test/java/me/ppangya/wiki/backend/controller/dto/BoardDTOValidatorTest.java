package me.ppangya.wiki.backend.controller.dto;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
public class BoardDTOValidatorTest {

	private static Validator validator;

	@BeforeClass
	public static void beforeClass() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	public void boardDTOTitleNotNull() {
		BoardDTO boardDTO = new BoardDTO(null);
		Set<ConstraintViolation<BoardDTO>> constraintViolations = validator.validate(boardDTO);
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("may not be null", constraintViolations.iterator().next().getMessage());
	}

	@Test
	public void boardDTOTitleCheckSize() {
		BoardDTO boardDTO = new BoardDTO("");
		Set<ConstraintViolation<BoardDTO>> constraintViolations = validator.validate(boardDTO);
		Assert.assertEquals(1, constraintViolations.size());
		Assert.assertEquals("size must be between 5 and 100", constraintViolations.iterator().next().getMessage());
	}
}
