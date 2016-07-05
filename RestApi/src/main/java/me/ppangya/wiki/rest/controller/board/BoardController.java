package me.ppangya.wiki.rest.controller.board;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.rest.controller.dto.BoardDTO;
import me.ppangya.wiki.rest.exception.RestApiException;
import me.ppangya.wiki.rest.repository.entity.Board;
import me.ppangya.wiki.rest.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class BoardController {

	private @Autowired BoardService boardService;

	@PostMapping(value = "/board")
	public Board postBoard(@Valid @RequestBody BoardDTO boardDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new RestApiException(HttpStatus.BAD_REQUEST, bindingResult);
		}
		return boardService.insertBoard(boardDTO.getTitle());
	}

	@GetMapping(value = "/board/{boardId:^[1-9]\\d*$}")
	public Board getBoard(@PathVariable Long boardId) {
		return boardService.selectOneByBoardId(boardId);
	}

	@PutMapping(value = "/board/{boardId:^[1-9]\\d*$}")
	public Board putBoard(@PathVariable Long boardId, @RequestBody BoardDTO boardDTO) {
		return boardService.updateBoard(boardId, boardDTO.getTitle());
	}

	@DeleteMapping(value = "/board/{boardId:^[1-9]\\d*$}")
	public void deleteBoard(@PathVariable Long boardId) {
		boardService.deleteBoard(boardId);
	}

}
