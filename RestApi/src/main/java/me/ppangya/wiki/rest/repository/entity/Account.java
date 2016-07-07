package me.ppangya.wiki.rest.repository.entity;

import lombok.*;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Account {

	private @Id @GeneratedValue @Getter @Setter @Column(name = "account_id", nullable = false, unique = true) Long accountId;
	private @Getter @Setter @Column(name = "first_name", nullable = true, length = 100) String firstName;
	private @Getter @Setter @Column(name = "last_name", nullable = true, length = 100) String lastName;
	private @Getter @Setter @Column(name = "email", nullable = true, length = 100) String email;
	private @Getter @Setter @Column(name = "create_date", nullable = true) Date createDate;

}