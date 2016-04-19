package me.ppangya.wiki.backend.repository.board.mybatis.sqlite;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.framework.annotation.MybatisRepository;

@MybatisRepository
public class BoardRepositoryImpl implements BoardRepository {
	@Override
	public Board insert(Board board) {
		return null;
	}
}
