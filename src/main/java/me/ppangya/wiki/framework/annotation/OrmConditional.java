package me.ppangya.wiki.framework.annotation;

import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OrmSystemPropertyCondition.class)
public @interface OrmConditional {

	SystemProperties.ObjectRelationalMapping[] values();
}
