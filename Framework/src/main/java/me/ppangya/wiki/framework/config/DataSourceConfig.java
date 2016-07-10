package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.annotation.DatabaseConditional;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import static me.ppangya.wiki.framework.constant.SystemProperties.Database;
import static me.ppangya.wiki.framework.constant.SystemProperties.ObjectRelationalMapping;

@Configuration
public class DataSourceConfig {

	/**
	 * Sqlite
	 */

	private @Value("${me.ppangya.wiki.sqlite.jdbc.driver.class.name}") String sqliteDriverClassName;
	private @Value("${me.ppangya.wiki.sqlite.jdbc.url}") String sqliteUrl;
	private @Value("${me.ppangya.wiki.sqlite.jdbc.initializer.enabled}") Boolean sqliteInitializerEnabled;

	private @Value("classpath:database/scheme/sqlite_init.sql") Resource sqliteInitDatabaseResource;

	@DatabaseConditional(value = Database.SQLITE)
	@Bean(name = "dataSource", destroyMethod = "close")
	public DataSource sqliteDataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(sqliteDriverClassName);
		dataSource.setUrl(sqliteUrl);
		return dataSource;
	}

	@DatabaseConditional(value = Database.SQLITE)
	@Bean(name = "dataSourceInitializer")
	public DataSourceInitializer sqliteDataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(sqliteDataSource());
		dataSourceInitializer.setEnabled(sqliteInitializerEnabled);
		dataSourceInitializer.setDatabasePopulator(sqliteDatabasePopulator());
		return dataSourceInitializer;
	}

	private DatabasePopulator sqliteDatabasePopulator() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(sqliteInitDatabaseResource);
		return resourceDatabasePopulator;
	}

	/**
	 * H2
	 */

	private @Value("${me.ppangya.wiki.h2.jdbc.driver.class.name}") String h2DriverClassName;
	private @Value("${me.ppangya.wiki.h2.jdbc.url}") String h2Url;
	private @Value("${me.ppangya.wiki.h2.jdbc.username}") String h2Username;
	private @Value("${me.ppangya.wiki.h2.jdbc.password}") String h2Password;
	private @Value("${me.ppangya.wiki.h2.jdbc.initializer.enabled}") Boolean h2InitializerEnabled;

	private @Value("classpath:database/scheme/h2_init.sql") Resource h2InitDatabaseResource;

	@DatabaseConditional(value = Database.H2)
	@Bean(name = "dataSource", destroyMethod = "close")
	public DataSource h2DataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(h2DriverClassName);
		dataSource.setUrl(h2Url);
		dataSource.setUsername(h2Username);
		dataSource.setPassword(h2Password);
		return dataSource;
	}

	@DatabaseConditional(value = Database.H2)
	@OrmConditional(values = {ObjectRelationalMapping.JDBC, ObjectRelationalMapping.MYBATIS})
	@Bean(name = "dataSourceInitializer")
	public DataSourceInitializer h2DataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(sqliteDataSource());
		dataSourceInitializer.setEnabled(h2InitializerEnabled);
		dataSourceInitializer.setDatabasePopulator(h2DatabasePopulator());
		return dataSourceInitializer;
	}

	private DatabasePopulator h2DatabasePopulator() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(h2InitDatabaseResource);
		return resourceDatabasePopulator;
	}

	/**
	 * MySQL
	 */
	private @Value("${me.ppangya.wiki.mysql.jdbc.driver.class.name}") String mysqlDriverClassName;
	private @Value("${me.ppangya.wiki.mysql.jdbc.url}") String mysqlUrl;
	private @Value("${me.ppangya.wiki.mysql.jdbc.username}") String mysqlUsername;
	private @Value("${me.ppangya.wiki.mysql.jdbc.password}") String mysqlPassword;
	private @Value("${me.ppangya.wiki.mysql.jdbc.initializer.enabled}") Boolean mysqlInitializerEnabled;

	private @Value("classpath:database/scheme/mysql_init.sql") Resource mysqlInitDatabaseResource;

	@DatabaseConditional(value = Database.MYSQL)
	@Bean(name="dataSource", destroyMethod = "close")
	public DataSource mysqlDataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(mysqlDriverClassName);
		dataSource.setUrl(mysqlUrl);
		dataSource.setUsername(mysqlUsername);
		dataSource.setPassword(mysqlPassword);
		return dataSource;
	}

	@DatabaseConditional(value = Database.MYSQL)
	@OrmConditional(values = {ObjectRelationalMapping.JPA, ObjectRelationalMapping.MYBATIS, ObjectRelationalMapping.JDBC})
	@Bean(name = "dataSourceInitializer")
	public DataSourceInitializer mysqlDataSourceInitializer() {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(mysqlDataSource());
		dataSourceInitializer.setEnabled(mysqlInitializerEnabled);
		dataSourceInitializer.setDatabasePopulator(mysqlDatabasePopulator());
		return dataSourceInitializer;
	}

	private DatabasePopulator mysqlDatabasePopulator() {
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScript(mysqlInitDatabaseResource);
		return resourceDatabasePopulator;
	}

}
