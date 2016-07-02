package me.ppangya.wiki.backend.repository.category.mybatis;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.test.annotation.MybatisTransactionalTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@MybatisTransactionalTest
public class MybatisCategoryImplTest {

	private @Autowired CategoryRepository categoryRepository;

	@Test
	public void saveTest() {
		Category category = new Category(null, "Category Name");
		category = categoryRepository.save(category);
		Assert.assertNotNull(category);
		Assert.assertNotNull(category.getCategoryId());
		Assert.assertNotNull(category.getName());
	}

	@Test
	public void findAllTest() {
		categoryRepository.save(new Category(null, "Category Name"));
		categoryRepository.save(new Category(null, "Category Name2"));
		Optional<List<Category>> categoryOptional = categoryRepository.findAll();
		Long count = categoryOptional.map(Collection::stream).orElse(Stream.<Category>empty()).count();
		Assert.assertTrue(count > 0);
	}

	@Test
	public void findOneTest() {
		Category category = categoryRepository.save(new Category(null, "name"));
		Long categoryId = category.getCategoryId();

		Optional<Category> categoryOptional = categoryRepository.findOne(categoryId);
		Category findCategory = categoryOptional.orElse(null);
		Assert.assertNotNull(findCategory);
		Assert.assertEquals(categoryId, findCategory.getCategoryId());
	}

	@Test
	public void deleteTest() {
		Category category = categoryRepository.save(new Category(null, "name"));
		Long categoryId = category.getCategoryId();
		categoryRepository.delete(category);

		Optional<Category> categoryOptional = categoryRepository.findOne(categoryId);
		Category findCategory = categoryOptional.orElse(null);
		Assert.assertNull(findCategory);
	}

}
