package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.annotation.MybatisRepository;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(useDefaultFilters = false, basePackages = "me.ppangya.wiki.backend.repository", includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = MybatisRepository.class) })
public class MybatisTransactionMangerConfig {

	private @Autowired DataSource dataSource;

	private @Value("database/mybatis/mybatis-config.xml") Resource mybatisConfigResource;
	private @Value("database/mybatis/*Mapper.xml") Resource[] mybatisMapperResources;

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(mybatisConfigResource);
		sqlSessionFactoryBean.setMapperLocations(mybatisMapperResources);
		return sqlSessionFactoryBean;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public PlatformTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}