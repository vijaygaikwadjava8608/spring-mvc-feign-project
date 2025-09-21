package com.grocery.category_service.service;

import com.grocery.category_service.entity.Category;
import com.grocery.category_service.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    Logger log = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository repository;
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    // Additional methods for category management can be added here
    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        List<Category> categories = repository.findAll();
        log.debug( "Found {} categories", categories.size());
        return categories;
    }
    public Optional<Category> createCategory(Category category) {

        log.info("Creating new category: {}", category.getName());
        Optional<Category> existingCategory = repository.findByName(category.getName());
        if (existingCategory.isPresent()) {
            log.warn("Category with name '{}' already exists", category.getName());
            return existingCategory; // or throw an exception
        }
        else {
            Category savedCategory = repository.save(category);
            log.debug("Category created with ID: {}", savedCategory.getId());
            return Optional.of(savedCategory);
        }


    }

    public void deleteCategory(Long id) {
        log.debug("Deleting category with ID: {}", id);
        repository.deleteById(id);
    }

    public Category updateCategory(Long id, Category category) {
        log.info("Updating category with ID: {}", id);
        if (repository.existsById(id)) {
            log.debug("Category with ID: {} exists. Proceeding with update.", id);
            category.setId(id);
            return repository.save(category);
        }
        return null; // or throw an exception
    }


    public Optional<Category> getCategoryById(Long id) {
        log.info("Fetching category with ID: {}", id);
        return repository.findById(id);
    }
}
