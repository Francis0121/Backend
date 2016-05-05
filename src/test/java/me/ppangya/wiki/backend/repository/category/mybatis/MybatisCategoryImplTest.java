package me.ppangya.wiki.backend.repository.category.mybatis;

import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.test.annotation.MybatisTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTransactionalTest
public class MybatisCategoryImplTest {

	private static final Logger logger = LoggerFactory.getLogger(MybatisCategoryImplTest.class);

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

    @Test
    public void findTest() {
        saveTest();
        Optional<List<Category>> category = categoryRepository.findAll();
        Assert.assertNotNull(category);
    }


}
