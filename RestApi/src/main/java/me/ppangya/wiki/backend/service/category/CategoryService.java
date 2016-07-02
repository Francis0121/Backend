package me.ppangya.wiki.backend.service.category;

import me.ppangya.wiki.backend.repository.entity.Category;

public interface CategoryService {

	Category insertCategory(Category category);

	Category updateCategory(Long categoryId, String name);

	void deleteCategory(Long categoryId);

	Category selectOneByCategoryId(Long categoryId);
}
