package me.ppangya.wiki.backend.controller.board;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.backend.service.BoardService;
import me.ppangya.wiki.framework.config.DispatcherServletConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = DispatcherServletConfig.class)
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
		mockMvc.perform(get("/board/1")).andExpect(status().isOk());
	}
}
