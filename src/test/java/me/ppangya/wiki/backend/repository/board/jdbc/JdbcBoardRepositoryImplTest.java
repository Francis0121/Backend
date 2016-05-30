package me.ppangya.wiki.backend.repository.board.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.exception.ResourceNotFoundException;
import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.test.annotation.JdbcTransactionalTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@JdbcTransactionalTest
public class JdbcBoardRepositoryImplTest {

	private @Autowired BoardRepository boardRepository;

	private Board initBoard;

	@Before
	public void before() {
		initBoard = new Board(null, "Board Title");
	}

	@Test
	public void saveTest_insertEntity() {
		Board board = boardRepository.save(initBoard);
		Assert.assertNotNull(board);
		Assert.assertNotNull(board.getBoardId());
		Assert.assertNotNull(board.getTitle());
	}

	@Test
	public void saveTest_updateEntity() {
		Board board = boardRepository.save(initBoard);
		board.setTitle("Modify Board Title");

		Board modifyBoard = boardRepository.save(board);
		Assert.assertNotNull(modifyBoard);
		Assert.assertEquals(board.getBoardId(), modifyBoard.getBoardId());
		Assert.assertEquals(board.getTitle(), modifyBoard.getTitle());
	}

	@Test
	public void findOneTest() {
		Long boardId = boardRepository.save(initBoard).getBoardId();

		Optional<Board> boardOptional = boardRepository.findOne(boardId);
		Board board = boardOptional.orElse(null);
		Assert.assertNotNull(board);
		Assert.assertEquals(boardId, board.getBoardId());
		Assert.assertEquals(initBoard.getTitle(), board.getTitle());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void deleteTest() {
		Board board = boardRepository.save(initBoard);
		Long boardId = board.getBoardId();
		boardRepository.delete(board);

		Optional<Board> boardOptional = boardRepository.findOne(boardId);
		Board findBoard = boardOptional.orElse(null);
		Assert.assertNull(findBoard);
	}
}
