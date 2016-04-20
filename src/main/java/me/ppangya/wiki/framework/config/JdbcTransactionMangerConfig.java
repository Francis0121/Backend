package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import me.ppangya.wiki.framework.util.OrmRepositoryFilter;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@OrmConditional(values = SystemProperties.ObjectRelationalMapping.JDBC)
@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@ComponentScan(useDefaultFilters = false, basePackages = "me.ppangya.wiki.backend.repository", includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = OrmRepositoryFilter.class)})
public class JdbcTransactionMangerConfig {

	private @Autowired @Qualifier("sqliteDataSource") DataSource dataSource;

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public PlatformTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
