package me.ppangya.wiki.backend.repository.category.jpa;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.test.annotation.JpaTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@JpaTransactionalTest
public class JpaCategoryRepositoryImplTest {

	private @Autowired CategoryRepository categoryRepository;

	@Test
	public void saveTest() {
		Category category = new Category(null, "Category name");
		category = categoryRepository.save(category);
		Assert.assertNotNull(category);
	}

}
