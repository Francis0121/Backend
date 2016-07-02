package me.ppangya.wiki.backend.service.category;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.exception.ResourceNotFoundException;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	private @Autowired CategoryRepository categoryRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Category insertCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Category updateCategory(Long categoryId, String name) {
		Optional<Category> categoryOptional = categoryRepository.findOne(categoryId);
		Category category = categoryOptional.orElseThrow(() -> new ResourceNotFoundException("categoryId={}", categoryId));
		category.setName(name);
		return categoryRepository.save(category);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public void deleteCategory(Long categoryId) {
		Optional<Category> categoryOptional = categoryRepository.findOne(categoryId);
		categoryOptional.ifPresent(categoryRepository::delete);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public Category selectOneByCategoryId(Long categoryId) {
		return categoryRepository.findOne(categoryId)
			.orElseThrow(() -> new ResourceNotFoundException("categoryId={}", categoryId));
	}
}
