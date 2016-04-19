package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.test.annotation.DefaultTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@DefaultTransactionalTest
public class RootApplicationConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(RootApplicationConfigTest.class);

	private @Autowired ApplicationContext applicationContext;
	private @Value("${me.ppangya.wiki.backend}") String properties;

	@Test
	public void rootApplicationConfigBeanCreateTest() {
		Assert.assertNotNull(applicationContext);
		Date date = new Date(applicationContext.getStartupDate());
		logger.info(String.valueOf(date));
		String[] beans = applicationContext.getBeanDefinitionNames();
		Optional<List<String>> beanListOptional = Optional.ofNullable(Arrays.asList(beans));
		beanListOptional.map(Collection::stream).orElse(Stream.<String>empty()).forEach(logger::info);

		Assert.assertNotNull(applicationContext.getBean("boardRepositoryImpl"));
	}

	@Test
	public void propertiesBeanCreateTest() {
		Assert.assertNotNull(properties);
		Assert.assertEquals("backend", properties);
	}

	@Test
	public void dataSourceBeanCreateTest() {
		Object dataSource = applicationContext.getBean("dataSource");
		Assert.assertNotNull(dataSource);
	}

}
