package me.ppangya.wiki.backend.service.board;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

	private @Autowired BoardRepository boardRepository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Board insertBoard(String title) {
		return boardRepository.save(new Board(null, title));
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Board updateBoard(Long boardId, String title) {
		Optional<Board> boardOptional = boardRepository.findOne(boardId);
		Board board = boardOptional.orElseThrow(RuntimeException::new);
		board.setTitle(title);
		return boardRepository.save(board);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public void deleteBoard(Long boardId) {
		Optional<Board> boardOptional = boardRepository.findOne(boardId);
		boardOptional.ifPresent(boardRepository::delete);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Board selectOneByBoardId(Long boardId) {
		return boardRepository.findOne(boardId).orElseThrow(RuntimeException::new);
	}
}
