package me.ppangya.wiki.backend.service.board;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

	private @Autowired BoardRepository boardRepository;

	@Override
	public Board insertBoard(String title) {
		return boardRepository.save(new Board(null, title));
	}

	@Override
	public Board updateBoard(Long boardId, String title) {
		Optional<Board> boardOptional = boardRepository.findOne(boardId);
		Board board = boardOptional.orElseThrow(RuntimeException::new);
		board.setTitle(title);
		return boardRepository.save(board);
	}

	@Override
	public void deleteBoard(Long boardId) {
		Optional<Board> boardOptional = boardRepository.findOne(boardId);
		boardOptional.ifPresent(boardRepository::delete);
	}

	@Override
	public Board selectOneByBoardId(Long boardId) {
		return boardRepository.findOne(boardId).orElseThrow(RuntimeException::new);
	}
}
