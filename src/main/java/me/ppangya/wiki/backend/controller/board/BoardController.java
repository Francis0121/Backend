package me.ppangya.wiki.backend.controller.board;

import me.ppangya.wiki.backend.controller.dto.BoardDTO;
import me.ppangya.wiki.backend.repository.entity.Board;
import me.ppangya.wiki.backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController {

	private @Autowired BoardService boardService;

	@PostMapping(value = "/board")
	public Board postBoard(@RequestBody BoardDTO boardDTO) {
		return boardService.insertBoard(boardDTO.getTitle());
	}

	@GetMapping(value = "/board/{boardId}")
	public Board getBoard(@PathVariable Long boardId) {
		return boardService.selectOneByBoardId(boardId);
	}

	@PutMapping(value = "/board/{boardId}")
	public Board putBoard(@PathVariable Long boardId, @RequestBody BoardDTO boardDTO) {
		return boardService.updateBoard(boardId, boardDTO.getTitle());
	}

	@DeleteMapping(value = "/board/{boardId}")
	public void deleteBoard(@PathVariable Long boardId) {
		boardService.deleteBoard(boardId);
	}

}
