package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.annotation.JdbcRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(useDefaultFilters = false,
               basePackages = "me.ppangya.wiki.backend.repository",
               includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = JdbcRepository.class), @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MybatisTransactionMangerConfig.class)})
public class MybatisTransactionMangerConfig {

}
