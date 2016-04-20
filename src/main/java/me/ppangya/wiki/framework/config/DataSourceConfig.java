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
public class DataSourceConfig {

	/**
	 * Sqlite
	 */

	private @Value("${me.ppangya.wiki.sqlite.jdbc.driver.class.name}") String sqliteDriverClassName;
	private @Value("${me.ppangya.wiki.sqlite.jdbc.url}") String sqliteUrl;
	private @Value("${me.ppangya.wiki.sqlite.jdbc.initializer.enabled}") Boolean sqliteInitializerEnabled;

	private @Value("database/scheme/init.sql") Resource initDatabaseResource;

	@Bean(destroyMethod = "close")
	public DataSource sqliteDataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(sqliteDriverClassName);
		dataSource.setUrl(sqliteUrl);
		return dataSource;
	}

	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(sqliteDataSource());
		dataSourceInitializer.setEnabled(sqliteInitializerEnabled);
		dataSourceInitializer.setDatabasePopulator(databasePopulator());
		return dataSourceInitializer;
	}

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(initDatabaseResource);
		return resourceDatabasePopulator;
	}

	/**
	 *  H2
	 */

	private @Value("${me.ppangya.wiki.h2.jdbc.driver.class.name}") String h2DriverClassName;
	private @Value("${me.ppangya.wiki.h2.jdbc.url}") String h2Url;
	private @Value("${me.ppangya.wiki.h2.jdbc.username}") String h2Username;
	private @Value("${me.ppangya.wiki.h2.jdbc.password}") String h2Password;

	@Bean(destroyMethod = "close")
	public DataSource h2DataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(h2DriverClassName);
		dataSource.setUrl(h2Url);
		dataSource.setUsername(h2Username);
		dataSource.setPassword(h2Password);
		return dataSource;
	}
}
