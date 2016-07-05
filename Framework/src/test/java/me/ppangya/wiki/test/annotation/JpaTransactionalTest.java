package me.ppangya.wiki.test.annotation;

import me.ppangya.wiki.framework.config.RootApplicationConfig;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static me.ppangya.wiki.framework.constant.SystemProperties.OBJECT_RELATIONAL_MAPPING_NAME;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = RootApplicationConfig.class)
@Transactional
@Rollback
@IfProfileValue(name = OBJECT_RELATIONAL_MAPPING_NAME, value = "jpa")
public @interface JpaTransactionalTest {
}
