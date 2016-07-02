package me.ppangya.wiki.backend.repository.board.mybatis;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Repository
@OrmConditional(values = SystemProperties.ObjectRelationalMapping.MYBATIS)
public class BoardRepositoryImpl implements BoardRepository {

	private @Autowired SqlSessionTemplate sqlSessionTemplate;

	@Override
	public <S extends Board> Board save(S board) {
		Optional<Board> boardOptional = this.findOne(board.getBoardId());
		Board findBoard = boardOptional.orElseGet(() -> {
			sqlSessionTemplate.insert("board.insert", board);
			return board;
		});
		findBoard.setTitle(board.getTitle());
		sqlSessionTemplate.update("board.update", findBoard);
		return findBoard;
	}

	@Override
	public void delete(Board board) {
		sqlSessionTemplate.delete("board.delete", board.getBoardId());
	}

	@Override
	public Optional<Board> findOne(Long id) {
		return Optional.ofNullable(sqlSessionTemplate.selectOne("board.findOne", id));
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

