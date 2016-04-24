package me.ppangya.wiki.framework.config;

import me.ppangya.wiki.framework.constant.SystemProperties;
import me.ppangya.wiki.test.annotation.JpaTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@JpaTransactionalTest
public class JpaTransactionMangerConfigTest {

	private @Autowired ApplicationContext applicationContext;

	@Test
	public void systemEnvironmentBeanCreateTest() {
		Object object = applicationContext.getBean("systemProperties");
		Assert.assertNotNull(object);
		Assert.assertTrue(object instanceof SystemProperties);
		SystemProperties systemProperties = (SystemProperties) object;
		SystemProperties.ObjectRelationalMapping objectRelationalMapping = systemProperties.getObjectRelationalMapping();
		Assert.assertEquals(SystemProperties.ObjectRelationalMapping.JPA, objectRelationalMapping);
	}

	@Test
	public void localContainerEntityManagerFactoryBeanBeanCreateTest() {
		Object object = applicationContext.getBean("localContainerEntityManagerFactoryBean");
		Assert.assertNotNull(object);
	}

	@Test
	public void dataSourceTransactionManagerBeanCreateTest() {
		Object object = applicationContext.getBean("dataSourceTransactionManager");
		Assert.assertNotNull(object);
	}
}
