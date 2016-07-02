package me.ppangya.wiki.backend.repository.board.jpa;

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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@JpaTransactionalTest
public class JpaBoardRepositoryImplTest {

    private static final Logger logger = LoggerFactory.getLogger(JpaBoardRepositoryImplTest.class);

    private
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void saveTest() {
        Board board = new Board(null, "Board Title");
        board = boardRepository.save(board);
        Assert.assertNotNull(board);
        Assert.assertNotNull(board.getBoardId());
        Assert.assertNotNull(board.getTitle());
        logger.debug("{}", board);
    }

    @Test
    public void findOneTest() {
        Board board = new Board(null, "Board Title");
        board = boardRepository.save(board);

        Optional<Board> boardOptional = boardRepository.findOne(board.getBoardId());
        Assert.assertNotNull(boardOptional);

        Board findBoard = boardOptional.orElseThrow(RuntimeException::new);
        Assert.assertEquals(board.getBoardId(), findBoard.getBoardId());
        Assert.assertEquals(board.getTitle(), findBoard.getTitle());
        logger.debug("{}", board);
    }

    @Test
    public void findListByTitleOrderByBoardIdDescTest() {
        List<Board> boardList = Arrays.asList(new Board(null, "Title"), new Board(null, "Title"));
        boardList.stream().forEach(boardRepository::save);

        Stream<Board> boardStream = boardRepository.findListByTitleOrderByBoardIdDesc("Title");
        Assert.assertThat(boardStream.count(), is(2L));
    }

}
