package me.ppangya.wiki.backend.repository.board.jdbc.sqlite;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.framework.annotation.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.Optional;

@JdbcRepository("jdbcBoardRepositoryImpl")
public class JdbcBoardRepositoryImpl implements BoardRepository {

	private @Autowired JdbcTemplate jdbcTemplate;

	private static final String INSERT_SQL = "INSERT INTO BOARD (title) VALUES (?)";

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public Board save(Board board) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"board_id"});
			ps.setString(1, board.getTitle());
			return ps;
		}, keyHolder);
		return new Board(keyHolder.getKey().longValue(), board.getTitle());
	}

	@Override
	public Optional<Board> findOne(Long id) {
		return null;
	}
}
