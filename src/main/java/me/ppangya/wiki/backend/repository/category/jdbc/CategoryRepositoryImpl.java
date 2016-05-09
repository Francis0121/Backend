package me.ppangya.wiki.backend.repository.category.jdbc;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public <S extends Category> Category save(S category) {
		log.debug("Save Parameter : {}", category);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SAVE_SQL, new String[]{"name"});
			ps.setString(1, category.getName());
			return ps;
		}, keyHolder);
		return new Category(keyHolder.getKey().longValue(), category.getName());
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public Optional<List<Category>> findAll() {
		List<Category> categoryList = jdbcTemplate.query(FIND_ALL_SQL, new CategoryMapper());
		log.debug("find : {}", categoryList);
		return Optional.ofNullable(categoryList);
	}

	@Override
	public void update(Category category) {


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


