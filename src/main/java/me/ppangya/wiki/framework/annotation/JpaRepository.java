package me.ppangya.wiki.framework.annotation;

import java.lang.annotation.*;

import org.springframework.stereotype.Component;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface JpaRepository {
}
