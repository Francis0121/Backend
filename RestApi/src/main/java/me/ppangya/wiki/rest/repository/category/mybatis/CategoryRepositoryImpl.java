package me.ppangya.wiki.rest.repository.category.mybatis;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.rest.repository.category.CategoryRepository;
import me.ppangya.wiki.rest.repository.entity.Category;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@OrmConditional(values = SystemProperties.ObjectRelationalMapping.MYBATIS)
public class CategoryRepositoryImpl implements CategoryRepository {

	private @Autowired SqlSessionTemplate sqlSessionTemplate;

	@Override
	public <S extends Category> Category save(S category) {
		Optional<Category> categoryOptional = findOne(category.getCategoryId());
		Category findCategory = categoryOptional.orElseGet(() -> {
			sqlSessionTemplate.insert("category.insert", category);
			return category;
		});
		findCategory.setName(category.getName());
		sqlSessionTemplate.update("category.update", findCategory);
		return findCategory;
	}

	@Override
	public Optional<List<Category>> findAll() {
		List<Category> categoryList = sqlSessionTemplate.selectList("category.findAll");
		return Optional.ofNullable(categoryList);
	}

	@Override
	public Optional<Category> findOne(Long categoryId) {
		return Optional.ofNullable(sqlSessionTemplate.selectOne("category.findOne", categoryId));
	}

	@Override
	public void delete(Category category) {
		Optional<Category> categoryOptional = findOne(category.getCategoryId());
		categoryOptional.ifPresent(findCategory -> sqlSessionTemplate.delete("category.delete", category.getCategoryId()));
	}

}

