package com.example.store.services;

import com.example.store.dtos.CategoryRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Category;
import com.example.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoría con el id " + id + " no encontrada")
        );
    }

    public Category createCategory(CategoryRequest newCategory) {
        Category category = new Category();
        category.setCategoryName(newCategory.categoryName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, CategoryRequest updateCategory) {
        Category oldCategory = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoría con el id " + id + " no encontrada")
        );
        if (updateCategory.categoryName() != null) {
            oldCategory.setCategoryName(updateCategory.categoryName());
        }
        return categoryRepository.save(oldCategory);
    }

    public Category deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoría con el id " + id + " no encontrada")
        );
        categoryRepository.delete(category);
        return category;
    }
}