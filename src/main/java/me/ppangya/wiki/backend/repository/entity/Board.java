package me.ppangya.wiki.backend.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOARD")
public class Board {

	private @Id @GeneratedValue @Column(name = "board_id", nullable = false, unique = true) Long boardId;
	private @Column(name = "title", nullable = true, length = 100) String title;

	public Board() {
	}

	public Board(Long boardId, String title) {
		this.boardId = boardId;
		this.title = title;
	}

	public Board(String title) {
		this.title = title;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Board{");
		sb.append("boardId=").append(boardId);
		sb.append(", title='").append(title).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
