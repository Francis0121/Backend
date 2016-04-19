package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.constant.SystemEnvironment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Stream;

import static me.ppangya.wiki.framework.constant.SystemEnvironment.ObjectRelationalMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationConfig.class)
public class RootApplicationConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(RootApplicationConfigTest.class);

	private @Autowired ApplicationContext applicationContext;
	private @Value("${me.ppangya.wiki.backend}") String properties;

	@Test
	public void initRootApplicationConfigTest() {
		Assert.assertNotNull(applicationContext);
		Date date = new Date(applicationContext.getStartupDate());
		logger.info(String.valueOf(date));
		String[] beans = applicationContext.getBeanDefinitionNames();
		Optional<List<String>> beanListOptional = Optional.ofNullable(Arrays.asList(beans));
		beanListOptional.map(Collection::stream).orElse(Stream.<String>empty()).forEach(logger::info);
	}

	@Test
	public void initPropertiesTest() {
		Assert.assertNotNull(properties);
		Assert.assertEquals("backend", properties);
	}

	@Test
	public void initDataSourceTest() {
		Object dataSource = applicationContext.getBean("dataSource");
		Assert.assertNotNull(dataSource);
	}

	@Test
	public void initJdbcTemplateTest() {
		Object jdbcTemplate = applicationContext.getBean("jdbcTemplate");
		Assert.assertNotNull(jdbcTemplate);
	}

	@Test
	public void initSystemEnvironment() {
		Object object = applicationContext.getBean("systemEnvironment");
		Assert.assertNotNull(object);
		Assert.assertTrue(object instanceof SystemEnvironment);
		SystemEnvironment systemEnvironment = (SystemEnvironment) object;
		ObjectRelationalMapping objectRelationalMapping = systemEnvironment.getObjectRelationalMapping();
		Assert.assertEquals(ObjectRelationalMapping.JDBC, objectRelationalMapping);
	}
}
