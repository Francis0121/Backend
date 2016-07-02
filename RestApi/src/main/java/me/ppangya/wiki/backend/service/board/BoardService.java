package me.ppangya.wiki.backend.service.board;

import me.ppangya.wiki.backend.repository.entity.Board;

public interface BoardService {

	Board insertBoard(String title);

	Board updateBoard(Long boardId, String title);

	void deleteBoard(Long boardId);

	Board selectOneByBoardId(Long boardId);
}
