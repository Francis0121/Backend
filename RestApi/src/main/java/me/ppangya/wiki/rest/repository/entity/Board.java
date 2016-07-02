package me.ppangya.wiki.rest.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "BOARD")
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Board {

	private @Id @GeneratedValue @Getter @Setter @Column(name = "board_id", nullable = false, unique = true) Long boardId;
	private @Getter @Setter @Column(name = "title", nullable = true, length = 100) String title;

}
