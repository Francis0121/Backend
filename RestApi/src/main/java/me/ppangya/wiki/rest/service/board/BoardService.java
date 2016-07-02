package me.ppangya.wiki.rest.service.board;

import me.ppangya.wiki.rest.repository.entity.Board;

public interface BoardService {

	Board insertBoard(String title);

	Board updateBoard(Long boardId, String title);

	void deleteBoard(Long boardId);

	Board selectOneByBoardId(Long boardId);
}
