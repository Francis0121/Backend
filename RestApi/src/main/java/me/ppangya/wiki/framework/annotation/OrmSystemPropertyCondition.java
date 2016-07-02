package me.ppangya.wiki.framework.annotation;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.framework.constant.Environment;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import static me.ppangya.wiki.framework.constant.SystemProperties.OBJECT_RELATIONAL_MAPPING_NAME;
import static me.ppangya.wiki.framework.constant.SystemProperties.ObjectRelationalMapping;

@Slf4j
public class OrmSystemPropertyCondition implements Condition {

	@Override
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
		Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(OrmConditional.class.getName());
		List<ObjectRelationalMapping> objectRelationalMappingList = Arrays.asList((ObjectRelationalMapping[]) annotationAttributes
			.get("values"));

		List<String> objectRelationalMappingNameList = objectRelationalMappingList.stream()
			.map(Enum::name)
			.map(String::toLowerCase)
			.collect(Collectors.toList());

		String ormProperty = System.getProperty(OBJECT_RELATIONAL_MAPPING_NAME);
		BooleanSupplier isOrmProperty = () -> StringUtils.isEmpty(ormProperty) || !Environment.OBJECT_RELATIONAL_MAPPING_NAME_LIST
			.contains(ormProperty);

		if (isOrmProperty.getAsBoolean()) {
			throw new RuntimeException(ormProperty);
		}

		return objectRelationalMappingNameList.contains(ormProperty);
	}
}
