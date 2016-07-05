package me.ppangya.wiki.rest.service.category;

import me.ppangya.wiki.rest.repository.entity.Category;

public interface CategoryService {

	Category insertCategory(Category category);

	Category updateCategory(Long categoryId, String name);

	void deleteCategory(Long categoryId);

	Category selectOneByCategoryId(Long categoryId);
}
