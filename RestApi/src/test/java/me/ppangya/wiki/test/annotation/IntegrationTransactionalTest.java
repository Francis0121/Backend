package me.ppangya.wiki.test.annotation;

import me.ppangya.wiki.framework.config.DispatcherServletConfig;
import me.ppangya.wiki.framework.config.RootApplicationConfig;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = {RootApplicationConfig.class, DispatcherServletConfig.class})
@Transactional
@WebAppConfiguration
@Rollback
public @interface IntegrationTransactionalTest {
}
