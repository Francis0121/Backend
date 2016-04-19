package me.ppangya.wiki.framework.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootApplicationConfig.class)
public class RootApplicationConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(RootApplicationConfigTest.class);

	private @Autowired ApplicationContext applicationContext;

	@Test
	public void initRootApplicationConfig() {
		Assert.assertNotNull(applicationContext);
		Date date = new Date(applicationContext.getStartupDate());
		logger.info(String.valueOf(date));
	}
}
