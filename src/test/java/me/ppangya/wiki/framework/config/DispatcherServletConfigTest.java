package me.ppangya.wiki.framework.config;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.test.annotation.IntegrationTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTransactionalTest
public class DispatcherServletConfigTest {

	private @Autowired WebApplicationContext webApplicationContext;

	@Test
	public void initWebApplicationContextTest() {
		Assert.assertNotNull(webApplicationContext);
		Date date = new Date(webApplicationContext.getStartupDate());
		Assert.assertNotNull(date);
		log.debug("StartupDate : {}", date);
		log.debug("BeanDefinition Count : {}", webApplicationContext.getBeanDefinitionCount());
		Optional<List<String>> beanDefinitionNameListOptional = Optional.ofNullable(Arrays.asList(webApplicationContext.getBeanDefinitionNames()));
		beanDefinitionNameListOptional.map(Collection::stream).orElse(Stream.<String>empty()).forEach(log::debug);
	}

}
