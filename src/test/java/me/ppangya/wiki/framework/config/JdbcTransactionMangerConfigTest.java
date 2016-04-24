package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.constant.SystemProperties;
import me.ppangya.wiki.test.annotation.JdbcTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@JdbcTransactionalTest
public class JdbcTransactionMangerConfigTest {

	private @Autowired ApplicationContext applicationContext;

	@Test
	public void systemEnvironmentBeanCreateTest() {
		Object object = applicationContext.getBean("systemProperties");
		Assert.assertNotNull(object);
		Assert.assertTrue(object instanceof SystemProperties);
		SystemProperties systemProperties = (SystemProperties) object;
		SystemProperties.ObjectRelationalMapping objectRelationalMapping = systemProperties.getObjectRelationalMapping();
		Assert.assertEquals(SystemProperties.ObjectRelationalMapping.JDBC, objectRelationalMapping);
	}

	@Test
	public void jdbcTemplateBeanCreateTest() {
		Object object = applicationContext.getBean("jdbcTemplate");
		Assert.assertNotNull(object);
	}

	@Test
	public void dataSourceTransactionManagerBeanCreateTest() {
		Object object = applicationContext.getBean("dataSourceTransactionManager");
		Assert.assertNotNull(object);
	}

}
