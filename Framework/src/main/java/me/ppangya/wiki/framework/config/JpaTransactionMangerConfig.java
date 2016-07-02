package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

@OrmConditional(values = SystemProperties.ObjectRelationalMapping.JPA)
@Configuration
@EnableJpaRepositories(basePackages = "me.ppangya.wiki.rest.repository", entityManagerFactoryRef = "localContainerEntityManagerFactoryBean", transactionManagerRef = "dataSourceTransactionManager")
public class JpaTransactionMangerConfig {

	static {
		System.setProperty("org.jboss.logging.provider", "slf4j");
	}

	private @Autowired DataSource dataSource;

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
