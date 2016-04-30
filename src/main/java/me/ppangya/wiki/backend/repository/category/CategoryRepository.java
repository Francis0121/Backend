package me.ppangya.wiki.backend.repository.category;

import me.ppangya.wiki.backend.repository.entity.Category;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CategoryRepository extends Repository<Category, Long>, CategoryRepositoryCustom {

//	Optional<Category> findOne(Long id);

	<S extends Category> Category save(S category);
}
