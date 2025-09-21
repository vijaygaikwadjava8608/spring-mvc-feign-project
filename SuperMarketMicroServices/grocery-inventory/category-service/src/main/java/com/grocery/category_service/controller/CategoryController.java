package com.grocery.category_service.controller;

import com.grocery.category_service.entity.Category;
import com.grocery.category_service.service.CategoryService;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        logger.info("Fetching all categories");
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Optional<Category> createCategory(@RequestBody Category category) {
        logger.info("in controller - creating new category: {}", category.getName());
        return categoryService.createCategory(category);
    }
    // Additional endpoints for category management can be added here
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        logger.info("in controller - Deleting category with ID: {}", id);

        categoryService.deleteCategory(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        logger.info("in controller - Fetching category with ID: {}", id);
        return categoryService.getCategoryById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }






}
