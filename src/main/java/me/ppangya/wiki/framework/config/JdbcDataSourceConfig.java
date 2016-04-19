package me.ppangya.wiki.framework.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class JdbcDataSourceConfig {

	private @Value("${me.ppangya.wiki.jdbc.driver.class.name}") String driverClassName;
	private @Value("${me.ppangya.wiki.jdbc.url}") String url;
	private @Value("${me.ppangya.wiki.jdbc.initializer.enabled}") Boolean initializerEnabled;

	private @Value("scheme/init.sql") Resource initDatabaseResource;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		return dataSource;
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource());
		dataSourceInitializer.setEnabled(initializerEnabled);
		dataSourceInitializer.setDatabasePopulator(databasePopulator());
		return dataSourceInitializer;
	}

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(initDatabaseResource);
		return resourceDatabasePopulator;
	}
}
