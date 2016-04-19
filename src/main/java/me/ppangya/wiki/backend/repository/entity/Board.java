package me.ppangya.wiki.backend.repository.entity;

public class Board {

	private Long boardId;
	private String title;

	public Board() {
	}

	public Board(String title) {
		this.title = title;
	}

	public Long getBoardId() {
		return boardId;
	}

	public String getTitle() {
		return title;
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
