package me.ppangya.wiki.backend.repository.board.jpa.h2;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.test.annotation.JpaTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@JpaTransactionalTest
public class JpaBoardRepositoryImplTest {

	private static final Logger logger = LoggerFactory.getLogger(JpaBoardRepositoryImplTest.class);

	private @Autowired BoardRepository boardRepository;

	@Test
	public void saveTest() {
		Board board = new Board("Board Title");
		board = boardRepository.save(board);
		Assert.assertNotNull(board);
		Assert.assertNotNull(board.getBoardId());
		Assert.assertNotNull(board.getTitle());
		logger.debug("{}", board);
	}

	@Test
	public void findOneTest(){
		Board board = new Board("Board Title");
		board = boardRepository.save(board);

		Optional<Board> boardOptional = boardRepository.findOne(board.getBoardId());
		Assert.assertNotNull(boardOptional);
		Board findBoard = boardOptional.get();
		Assert.assertEquals(board.getBoardId(), findBoard.getBoardId());
		Assert.assertEquals(board.getTitle(), findBoard.getTitle());
		logger.debug("{}", board);
	}

}
