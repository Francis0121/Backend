package me.ppangya.wiki.backend.repository.board.mybatis.sqlite;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.test.annotation.MybatisTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTransactionalTest
public class MybatisRepositoryImplTest {

	private static final Logger logger = LoggerFactory.getLogger(MybatisRepositoryImplTest.class);

	private @Autowired BoardRepository boardRepository;

	@Test
	public void saveTest() {
		Board board = new Board("Board Title");
		board = boardRepository.save(board);
		Assert.assertNotNull(board);
		Assert.assertNotNull(board.getBoardId());
		Assert.assertNotNull(board.getTitle());
		logger.debug("Insert : {}", board);
	}

}
