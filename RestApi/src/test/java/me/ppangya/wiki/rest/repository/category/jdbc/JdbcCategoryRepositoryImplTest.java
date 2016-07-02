package me.ppangya.wiki.rest.repository.category.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.rest.exception.ResourceNotFoundException;
import me.ppangya.wiki.rest.repository.category.CategoryRepository;
import me.ppangya.wiki.rest.repository.entity.Category;
import me.ppangya.wiki.test.annotation.JdbcTransactionalTest;
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
@JdbcTransactionalTest
public class JdbcCategoryRepositoryImplTest {

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
	public void saveTest_update() {
		Category category = categoryRepository.save(new Category(null, "origin"));
		category.setName("change");

		Category modifyCategory = categoryRepository.save(category);
		Assert.assertNotNull(modifyCategory);
		Assert.assertEquals(category.getCategoryId(), modifyCategory.getCategoryId());
		Assert.assertEquals(category.getName(), modifyCategory.getName());
	}

	@Test
	public void findAllTest() {
		categoryRepository.save(new Category(null, "Category Name"));
		categoryRepository.save(new Category(null, "Name2"));
		Optional<List<Category>> categoryOptional = categoryRepository.findAll();
		Long count = categoryOptional.map(Collection::stream).orElse(Stream.<Category>empty()).count();
		Assert.assertTrue(count > 0);
	}

	@Test
	public void findOneTest() {
		Long categoryId = categoryRepository.save(new Category(null, "namegg")).getCategoryId();

		Optional<Category> categoryOptional = categoryRepository.findOne(categoryId);
		Category category = categoryOptional.orElse(null);
		Assert.assertNotNull(category);
		Assert.assertEquals(categoryId, category.getCategoryId());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void deleteTest() {
		Category category = categoryRepository.save(new Category(null, "name"));
		Long categoryId = category.getCategoryId();
		categoryRepository.delete(category);

		categoryRepository.findOne(categoryId);
	}
}
