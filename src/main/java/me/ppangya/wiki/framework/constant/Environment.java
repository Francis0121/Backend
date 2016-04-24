package me.ppangya.wiki.framework.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static me.ppangya.wiki.framework.constant.SystemProperties.ObjectRelationalMapping;

public interface Environment {

	String CHAR_SET = "UTF-8";

	List<SystemProperties.ObjectRelationalMapping> OBJECT_RELATIONAL_MAPPING_LIST = Arrays.asList(ObjectRelationalMapping
		.values());

	List<String> OBJECT_RELATIONAL_MAPPING_NAME_LIST = OBJECT_RELATIONAL_MAPPING_LIST.stream()
		.map(Enum::name)
		.map(String::toLowerCase)
		.collect(Collectors.toList());

	List<SystemProperties.Database> DATABASE_LIST = Arrays.asList(SystemProperties.Database.values());

	List<String> DATABASE_NAME_LIST = DATABASE_LIST.stream()
		.map(Enum::name)
		.map(String::toLowerCase)
		.collect(Collectors.toList());
}
