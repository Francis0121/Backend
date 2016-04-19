package me.ppangya.wiki.framework.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcDataSourceConfig {

	private @Value("${me.ppangya.wiki.jdbc.driver.classs.name}") String driverClassName;
	private @Value("${me.ppangya.wiki.jdbc.url}") String url;

	@Bean(name = "dataSource", destroyMethod = "close")
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		return dataSource;
	}
}
