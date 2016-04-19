package me.ppangya.wiki.backend.repository.board.sqlite;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

	private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryImpl.class);

	private @Autowired JdbcTemplate jdbcTemplate;

	@Override
	public Board insert(Board board) {
		String sql = "INSERT INTO BOARD (title) VALUES (?)";
		jdbcTemplate.update(sql, board.getTitle());
		logger.debug("insert {}", board);
		return board;
	}
}
