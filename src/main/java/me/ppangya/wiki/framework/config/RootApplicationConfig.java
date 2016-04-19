package me.ppangya.wiki.framework.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import(value = {JdbcDataSourceConfig.class, JdbcTemplateConfig.class})
@PropertySource(value = "classpath:properties/default.properties")
@ComponentScan(basePackages = "me.ppangya.wiki.backend.repository")
public class RootApplicationConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
