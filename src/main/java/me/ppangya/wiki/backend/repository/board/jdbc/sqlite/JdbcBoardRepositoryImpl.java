package me.ppangya.wiki.backend.repository.board.jdbc.sqlite;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JdbcBoardRepositoryImpl implements BoardRepository {

	private @Autowired JdbcTemplate jdbcTemplate;

	private static final String SAVE_SQL = "INSERT INTO BOARD (title) VALUES (?)";
	private static final String FIND_ONE_SQL = "SELECT * FROM BOARD WHERE board_id = ?";

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public <S extends Board> Board save(S board) {
		log.debug("Save Parameter : {}", board);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SAVE_SQL, new String[]{"board_id"});
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
