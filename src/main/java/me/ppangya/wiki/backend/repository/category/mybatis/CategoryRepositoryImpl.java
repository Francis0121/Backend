package me.ppangya.wiki.backend.repository.category.mybatis;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
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
		log.debug("Save Parameter : {}", category.toString());
		sqlSessionTemplate.insert("category.insert", category);
		return category;
	}

	@Override
	public Optional<List<Category>> findAll() {
		List<Category> categoryList = sqlSessionTemplate.selectList("category.findAll");
		return Optional.ofNullable(categoryList);
	}

}

