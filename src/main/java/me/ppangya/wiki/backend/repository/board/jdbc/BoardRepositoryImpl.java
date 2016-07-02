package me.ppangya.wiki.backend.repository.board.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Repository
@OrmConditional(values = SystemProperties.ObjectRelationalMapping.JDBC)
public class BoardRepositoryImpl implements BoardRepository {

	private @Autowired JdbcTemplate jdbcTemplate;

	private static final String INSERT_SQL = "INSERT INTO BOARD (title) VALUES (?)";
	private static final String UPDATE_SQL = "UPDATE BOARD SET title = ? WHERE board_id= ?";
	private static final String DELETE_SQL = "DELETE FROM BOARD WHERE board_id = ?";
	private static final String FIND_ONE_SQL = "SELECT board_id, title FROM BOARD WHERE board_id = ?";

	@Override
	public <S extends Board> Board save(S board) {
		log.debug("Save Parameter : {}", board);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"board_id"});
			ps.setString(1, board.getTitle());
			return ps;
		}, keyHolder);
		return new Board(keyHolder.getKey().longValue(), board.getTitle());
	}

	@Override
	public void delete(Board board) {

	}

	@Override
	public Optional<Board> findOne(Long id) {
		return null;
	}

	@Override
	public Stream<Board> findListByTitleOrderByBoardIdDesc(String title) {
		return null;
	}

	@Override
	public Optional<Board> findOneByBoardIdOrderByBoardIdAsc(Long boardId) {
		return null;
	}
}
