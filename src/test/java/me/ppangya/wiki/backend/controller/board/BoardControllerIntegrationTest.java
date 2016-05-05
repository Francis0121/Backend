package me.ppangya.wiki.backend.controller.board;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.service.BoardService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		boardService.insertBoard("Sample Title");

		mockMvc.perform(get("/board/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("boardId", is(1)))
			.andExpect(jsonPath("title", is("Sample Title")));
	}
}
