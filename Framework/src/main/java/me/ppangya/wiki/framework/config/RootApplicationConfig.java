package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.constant.Environment;
import me.ppangya.wiki.framework.constant.SystemProperties;
import me.ppangya.wiki.framework.constant.SystemProperties.Database;
import me.ppangya.wiki.framework.constant.SystemProperties.ObjectRelationalMapping;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.util.function.BooleanSupplier;

import static me.ppangya.wiki.framework.constant.SystemProperties.DATABASE_NAME;
import static me.ppangya.wiki.framework.constant.SystemProperties.OBJECT_RELATIONAL_MAPPING_NAME;

@Configuration
@Import(value = {DataSourceConfig.class, JdbcTransactionMangerConfig.class, MybatisTransactionMangerConfig.class, JpaTransactionMangerConfig.class})
@PropertySource(value = "classpath:properties/default.properties")
@ComponentScan(useDefaultFilters = false, basePackages = {"me.ppangya.wiki.rest.repository", "me.ppangya.wiki.rest.service"}, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class), @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class)})
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class RootApplicationConfig {

	@Bean
	public SystemProperties systemProperties() {
		String ormProperty = System.getProperty(OBJECT_RELATIONAL_MAPPING_NAME);
		BooleanSupplier isOrmProperty = () -> StringUtils.isEmpty(ormProperty) || !Environment.OBJECT_RELATIONAL_MAPPING_NAME_LIST
			.contains(ormProperty);

		if (isOrmProperty.getAsBoolean()) {
			throw new RuntimeException(ormProperty);
		}

		String databaseProperty = System.getProperty(DATABASE_NAME);
		BooleanSupplier isDatabaseProperty = () -> StringUtils.isEmpty(ormProperty) || !Environment.OBJECT_RELATIONAL_MAPPING_NAME_LIST
			.contains(ormProperty);

		if (isDatabaseProperty.getAsBoolean()) {
			throw new RuntimeException(databaseProperty);
		}

		return new SystemProperties(ObjectRelationalMapping.valueOf(ormProperty.toUpperCase()), Database.valueOf(databaseProperty
			.toUpperCase()));
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
