package me.ppangya.wiki.backend.repository.category.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Optional<Category> categoryOptional = findOne(category.getCategoryId());
		Category findCategory = categoryOptional.orElseGet(() -> {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SAVE_SQL, new String[]{"name"});
				ps.setString(1, category.getName());
				return ps;
			}, keyHolder);
			return new Category(keyHolder.getKey().longValue(), category.getName());
		});
		findCategory.setName(category.getName());
		jdbcTemplate.update(UPDATE_SQL, findCategory.getName(), findCategory.getCategoryId());
		return findCategory;
	}

	@Override
	public Optional<List<Category>> findAll() {
		List<Category> categoryList = jdbcTemplate.query(FIND_ALL_SQL, new CategoryMapper());
		return Optional.ofNullable(categoryList);
	}

	@Override
	public Optional<Category> findOne(Long categoryId) {
		Category category = null;
		try {
			category = jdbcTemplate.queryForObject(FIND_ONE_SQL, new Object[]{categoryId}, new CategoryMapper());
		} catch (EmptyResultDataAccessException e) {
			log.debug("category is not found");
		}
		return Optional.ofNullable(category);
	}

	@Override
	public void delete(Category category) {
		Optional<Category> categoryOptional = findOne(category.getCategoryId());
		categoryOptional.ifPresent(findCategory -> jdbcTemplate.update(DELETE_SQL, category.getCategoryId()));
	}

	private static final class CategoryMapper implements RowMapper<Category> {
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setCategoryId((long) rs.getInt("category_id"));
			category.setName(rs.getString("name"));
			return category;
		}
	}

}


