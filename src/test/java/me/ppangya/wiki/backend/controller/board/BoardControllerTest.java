package me.ppangya.wiki.backend.controller.board;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.backend.service.BoardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class BoardControllerTest {

	private MockMvc mockMvc;

	private @Mock BoardService boardService;
	private @InjectMocks BoardController boardController;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
	}

	@Test
	public void getBoard() throws Exception {
		when(boardService.selectOneByBoardId(1L)).thenReturn(new Board(1L, "Sample Title"));
		mockMvc.perform(get("/board/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("boardId", is(1)))
			.andExpect(jsonPath("title", is("Sample Title")));
	}

}
