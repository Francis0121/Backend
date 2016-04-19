package me.ppangya.wiki.backend.repository.board.mybatis.sqlite;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.framework.annotation.MybatisRepository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisRepository
public class BoardRepositoryImpl implements BoardRepository {

	private @Autowired SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Board insert(Board board) {
		sqlSessionTemplate.insert("board.insertBoard", board);
		return board;
	}
}
