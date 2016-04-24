package me.ppangya.wiki.framework.util;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.framework.constant.Environment;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;
import java.util.function.BooleanSupplier;

import static me.ppangya.wiki.framework.constant.SystemProperties.OBJECT_RELATIONAL_MAPPING_NAME;

@Slf4j
@Deprecated
public class OrmRepositoryFilter implements TypeFilter {

	@Override
	public boolean match(MetadataReader metadataReader,
	                     MetadataReaderFactory metadataReaderFactory) throws IOException {
		String ormProperty = System.getProperty(OBJECT_RELATIONAL_MAPPING_NAME);
		BooleanSupplier isOrmProperty = () -> StringUtils.isEmpty(ormProperty) || !Environment.OBJECT_RELATIONAL_MAPPING_NAME_LIST
			.contains(ormProperty);
		if (isOrmProperty.getAsBoolean()) {
			throw new RuntimeException(ormProperty);
		}

		String targetClass = metadataReader.getClassMetadata().getClassName();
		BooleanSupplier isTargetClass = () -> targetClass.contains(ormProperty);
		log.debug("class : {}, isTargetClass : {}", targetClass, isTargetClass.getAsBoolean());

		Set<String> annotationTypes = metadataReader.getAnnotationMetadata().getAnnotationTypes();
		BooleanSupplier isTargetAnnotation = () -> annotationTypes.stream()
			.filter(annotationType -> annotationType.contains(Repository.class.getSimpleName()))
			.count() > 0;
		log.debug("annotation : {}, isTargetAnnotation : {}", annotationTypes, isTargetAnnotation.getAsBoolean());
		return isTargetClass.getAsBoolean() && isTargetAnnotation.getAsBoolean();
	}

}
