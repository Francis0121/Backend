package me.ppangya.wiki.backend.controller.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.controller.dto.BoardDTO;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.backend.service.board.BoardService;
import me.ppangya.wiki.test.annotation.IntegrationTransactionalTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTransactionalTest
public class BoardControllerIntegrationTest {

	private @Autowired WebApplicationContext webApplicationContext;

	private @Autowired BoardService boardService;

	private MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getBoard() throws Exception {
		Board board = boardService.insertBoard("Sample Title");

		mockMvc.perform(get("/board/" + board.getBoardId()))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("boardId", is(board.getBoardId().intValue())))
			.andExpect(jsonPath("title", is(board.getTitle())));
	}

	@Test
	public void postBoard() throws Exception {
		BoardDTO boardDTO = new BoardDTO("Sample Title");
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer();
		String request = objectWriter.writeValueAsString(boardDTO);

		mockMvc.perform(post("/board").contentType(MediaType.APPLICATION_JSON_UTF8).content(request))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("boardId", notNullValue()))
			.andExpect(jsonPath("title", is(boardDTO.getTitle())));
	}

	@Test
	public void putBoard() throws Exception {
		Board board = boardService.insertBoard("Sample Title");
		BoardDTO boardDTO = new BoardDTO("Sample Modify Title");
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer();
		String request = objectWriter.writeValueAsString(boardDTO);

		mockMvc.perform(put("/board/" + board.getBoardId()).contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(request))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("boardId", notNullValue()))
			.andExpect(jsonPath("title", is(boardDTO.getTitle())));
	}

	@Test
	public void deleteBoard() throws Exception {
		Board board = boardService.insertBoard("Sample Title");

		mockMvc.perform(delete("/board/" + board.getBoardId())).andExpect(status().isOk());
	}

}
