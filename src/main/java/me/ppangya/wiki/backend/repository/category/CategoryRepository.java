package me.ppangya.wiki.backend.repository.category;

import me.ppangya.wiki.backend.repository.entity.Category;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends Repository<Category, Long>, CategoryRepositoryCustom {

	<S extends Category> Category save(S category);

	Optional<List<Category>> findAll();

	void update(Category category);
}
