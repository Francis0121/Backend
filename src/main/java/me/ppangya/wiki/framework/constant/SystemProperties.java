package me.ppangya.wiki.framework.constant;

import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class SystemProperties {

	public static final String OBJECT_RELATIONAL_MAPPING_NAME = "orm";

	public static final String DATABASE_NAME = "database";

	public enum ObjectRelationalMapping {
		JDBC,
		MYBATIS,
		JPA
	}

	public enum Database {
		SQLITE,
		H2
	}

	private @Getter ObjectRelationalMapping objectRelationalMapping;

	private @Getter Database database;
}
