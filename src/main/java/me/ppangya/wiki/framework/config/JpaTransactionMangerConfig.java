package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@Configuration
@OrmConditional(values = SystemProperties.ObjectRelationalMapping.JPA)
@EnableJpaRepositories(basePackageClasses = {BoardRepository.class}, excludeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = "jdbc.*"), @ComponentScan.Filter(type = FilterType.REGEX, pattern = "mybatis.*")}, entityManagerFactoryRef = "localContainerEntityManagerFactoryBean", transactionManagerRef = "dataSourceTransactionManager")
public class JpaTransactionMangerConfig {

	static {
		System.setProperty("org.jboss.logging.provider", "slf4j");
	}

	private @Autowired @Qualifier("h2DataSource") DataSource dataSource;

	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setJpaProperties(properties());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setPackagesToScan("me.ppangya.wiki.backend.repository.entity");
		return localContainerEntityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager dataSourceTransactionManager() {
		return new JpaTransactionManager(localContainerEntityManagerFactoryBean().getObject());
	}

	private JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	private Properties properties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", H2Dialect.class.getName());
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.use_sql_comments", "false");
		return properties;
	}

}
