package me.ppangya.wiki.backend.repository.category.jdbc;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.repository.category.CategoryRepository;
import me.ppangya.wiki.backend.repository.entity.Category;
import me.ppangya.wiki.framework.annotation.OrmConditional;
import me.ppangya.wiki.framework.constant.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@OrmConditional(values = SystemProperties.ObjectRelationalMapping.JDBC)
public class CategoryRepositoryImpl implements CategoryRepository {

	private @Autowired JdbcTemplate jdbcTemplate;

	private static final String SAVE_SQL = "INSERT INTO CATEGORY (name) VALUES (?)";
	private static final String FIND_ONE_SQL = "SELECT * FROM BOARD WHERE board_id = ?";
	private static final String FIND_ALL_SQL = "SELECT 'category_id', 'name' FROM CATEGORY";

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public <S extends Category> Category save(S category) {
		log.debug("Save Parameter : {}", category);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(SAVE_SQL, new String[]{"category_id"});
			ps.setString(1, category.getName());
			return ps;
		}, keyHolder);
		return new Category(keyHolder.getKey().longValue(), category.getName());
	}

	@Override
	public Optional<List<Category>> findAll() {
		return null;
	}

}
