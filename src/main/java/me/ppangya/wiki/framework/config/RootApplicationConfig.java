package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.constant.SystemEnvironment;
import me.ppangya.wiki.framework.constant.SystemEnvironment.ObjectRelationalMapping;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import static me.ppangya.wiki.framework.constant.SystemEnvironment.OBJECT_REALTIONAL_MAPPING_NAME;

@Configuration
@Import(value = {JdbcDataSourceConfig.class, JdbcTemplateConfig.class})
@PropertySource(value = "classpath:properties/default.properties")
@ComponentScan(basePackages = "me.ppangya.wiki.backend.repository")
public class RootApplicationConfig {

	@Bean
	public SystemEnvironment systemEnvironment() {
		ObjectRelationalMapping objectRelationalMapping = ObjectRelationalMapping.valueOf(System.getProperty(OBJECT_REALTIONAL_MAPPING_NAME).toUpperCase());
		return new SystemEnvironment(objectRelationalMapping);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
