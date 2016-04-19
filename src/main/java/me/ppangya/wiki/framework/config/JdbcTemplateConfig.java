package me.ppangya.wiki.framework.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class JdbcTemplateConfig {

	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateConfig.class);

	private @Autowired DataSource dataSource;

	@PostConstruct
	public void init() {
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS BOARD");
			statement.executeUpdate("CREATE TABLE BOARD( board_id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(100) NULL) ");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("Create Database Table", e);
		}
		logger.info("Create Database Table");
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}
