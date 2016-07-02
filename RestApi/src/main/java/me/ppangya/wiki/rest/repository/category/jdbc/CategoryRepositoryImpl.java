package me.ppangya.wiki.rest.repository.category.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.rest.exception.ResourceNotFoundException;
import me.ppangya.wiki.rest.repository.category.CategoryRepository;
import me.ppangya.wiki.rest.repository.entity.Category;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@OrmConditional(values = SystemProperties.ObjectRelationalMapping.JDBC)
public class CategoryRepositoryImpl implements CategoryRepository {

	private @Autowired JdbcTemplate jdbcTemplate;

	private static final String SAVE_SQL = "INSERT INTO CATEGORY (name) VALUES (?)";
	private static final String FIND_ONE_SQL = "SELECT category_id, name FROM CATEGORY WHERE category_id = ?";
	private static final String FIND_ALL_SQL = "SELECT category_id, name FROM CATEGORY";
	private static final String UPDATE_SQL = "UPDATE CATEGORY SET name = ? WHERE category_id = ?";
	private static final String DELETE_SQL = "DELETE FROM CATEGORY WHERE category_id = ?";

	@Override
	public <S extends Category> Category save(S category) {
		Long categoryId = category.getCategoryId();
		if (categoryId == null) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SAVE_SQL, new String[]{"name"});
				ps.setString(1, category.getName());
				return ps;
			}, keyHolder);
			return new Category(keyHolder.getKey().longValue(), category.getName());

		} else {
			Optional<Category> categoryOptional = findOne(categoryId);
			Category findCategory = categoryOptional.orElseThrow(() -> new ResourceNotFoundException("categoryId={}", categoryId));
			findCategory.setName(category.getName());
			jdbcTemplate.update(UPDATE_SQL, findCategory.getName(), findCategory.getCategoryId());
			return findCategory;
		}
	}

	@Override
	public Optional<List<Category>> findAll() {
		List<Category> categoryList = jdbcTemplate.query(FIND_ALL_SQL, (rs, rowNum) -> {
			return new Category(rs.getLong("category_id"), rs.getString("name"));
		});
		return Optional.ofNullable(categoryList);
	}

	@Override
	public Optional<Category> findOne(Long categoryId) {
		try {
			return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_ONE_SQL, new Object[]{categoryId}, (rs, rowNum) -> {
				return new Category(rs.getLong("category_id"), rs.getString("name"));
			}));
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("categoryId={}", categoryId);
		}
	}

	@Override
	public void delete(Category category) {
		Optional<Category> categoryOptional = findOne(category.getCategoryId());
		categoryOptional.ifPresent(findCategory -> jdbcTemplate.update(DELETE_SQL, category.getCategoryId()));
	}
}


