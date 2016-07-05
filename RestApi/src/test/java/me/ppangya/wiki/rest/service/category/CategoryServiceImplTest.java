package me.ppangya.wiki.rest.service.category;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.rest.exception.ResourceNotFoundException;
import me.ppangya.wiki.rest.repository.entity.Category;
import me.ppangya.wiki.test.annotation.DefaultTransactionalTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@DefaultTransactionalTest
public class CategoryServiceImplTest {

	private @Autowired CategoryService categoryService;

	@Test
	public void insertCategoryTest() {
		Category category = new Category(null, "New Category");

		Category insertCategory = categoryService.insertCategory(category);
		assertThat(insertCategory.getCategoryId(), is(not(nullValue())));
		assertThat(insertCategory.getName(), is(category.getName()));
	}

	@Test
	public void selectOneByCategoryIdTest() {
		Category category = categoryService.insertCategory(new Category(null, "New Category"));

		Category selectCategory = categoryService.selectOneByCategoryId(category.getCategoryId());
		assertThat(selectCategory.getCategoryId(), is(category.getCategoryId()));
		assertThat(selectCategory.getName(), is(category.getName()));
	}

	@Test
	public void updateCategoryTest() {
		Category category = categoryService.insertCategory(new Category(null, "New Category"));

		Category updateCategory = categoryService.updateCategory(category.getCategoryId(), "Modify Category");
		assertThat(updateCategory.getCategoryId(), is(category.getCategoryId()));
		assertThat(updateCategory.getName(), is("Modify Category"));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void deleteCategoryTest() {
		Category category = categoryService.insertCategory(new Category(null, "New Category"));
		Long categoryId = category.getCategoryId();
		categoryService.deleteCategory(categoryId);

		categoryService.selectOneByCategoryId(categoryId);
	}
}
