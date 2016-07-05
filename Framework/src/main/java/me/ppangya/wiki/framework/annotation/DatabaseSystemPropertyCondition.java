package me.ppangya.wiki.framework.annotation;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.framework.constant.Environment;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.function.BooleanSupplier;

import static me.ppangya.wiki.framework.constant.SystemProperties.DATABASE_NAME;

@Slf4j
public class DatabaseSystemPropertyCondition implements Condition {

	@Override
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
		Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(DatabaseConditional.class
			.getName());

		SystemProperties.Database database = (SystemProperties.Database) annotationAttributes.get("value");
		String databaseName = database.name().toLowerCase();

		String databaseProperty = System.getProperty(DATABASE_NAME);
		BooleanSupplier isDatabaseProperty = () -> StringUtils.isEmpty(databaseProperty) || !Environment.DATABASE_NAME_LIST
			.contains(databaseProperty);

		if (isDatabaseProperty.getAsBoolean()) {
			throw new RuntimeException(databaseProperty);
		}
		return databaseName.equals(databaseProperty);
	}
}
