package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.test.annotation.DefaultTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DefaultTransactionalTest
public class DataSourceConfigTest {

	private @Autowired ApplicationContext applicationContext;

	@Test
	public void dataSourceBeanCreateTest() {
		Object object = applicationContext.getBean("dataSource");
		Assert.assertNotNull(object);
	}

	@Test
	public void dataSourceInitializerBeanCreateTest() {
		Object object = applicationContext.getBean("dataSourceInitializer");
		Assert.assertNotNull(object);
	}
}
