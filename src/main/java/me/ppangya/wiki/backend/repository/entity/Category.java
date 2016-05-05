package me.ppangya.wiki.backend.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Category {

	private @Id @GeneratedValue @Getter @Setter @Column(name = "category_id", nullable = false, unique = true) Long categoryId;
	private @Getter @Setter @Column(name = "name", nullable = true, length = 100) String name;

}
