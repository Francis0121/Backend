package me.ppangya.wiki.backend.controller.category;

import lombok.extern.slf4j.Slf4j;
import me.ppangya.wiki.backend.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CategoryController {

	private @Autowired CategoryService categoryService;
}
