package me.ppangya.wiki.backend.service.board;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.backend.service.board.BoardService;
import me.ppangya.wiki.test.annotation.DefaultTransactionalTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@DefaultTransactionalTest
public class BoardServiceImplTest {

	private @Autowired BoardService boardService;

	@Test
	public void insertBoardTest() {
		Board board = boardService.insertBoard("Sample Title");
		log.debug(board.toString());
	}

}
