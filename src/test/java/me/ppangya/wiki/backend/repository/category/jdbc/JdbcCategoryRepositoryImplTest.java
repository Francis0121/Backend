package me.ppangya.wiki.backend.repository.category.jdbc;

import me.ppangya.wiki.backend.repository.board.BoardRepository;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.test.annotation.JdbcTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@JdbcTransactionalTest
public class JdbcCategoryRepositoryImplTest {

	private static final Logger logger = LoggerFactory.getLogger(JdbcCategoryRepositoryImplTest.class);

	private @Autowired CategoryRepository categoryRepository;

	@Test
	public void saveTest() {
		Category category = new Category(null, "Category Name");
		category = categoryRepository.save(category);
		Assert.assertNotNull(category);
		Assert.assertNotNull(category.getCategoryId());
		Assert.assertNotNull(category.getName());
		logger.debug("Insert : {}", category);
	}
}
