package com.example.store.controllers;

import com.example.store.dtos.CategoryRequest;
import com.example.store.models.Category;
import com.example.store.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/categories") // http://localhost:8080/api/categories
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping(path = "{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.createCategory(categoryRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
        return ResponseEntity.created(location).body(category);
    }

    @DeleteMapping(path = "{categoryId}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable("categoryId") Long id) {
        Category category = categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping(path = "{categoryId}")
    public ResponseEntity<Category> updateCategoryById(
            @PathVariable("categoryId") Long id,
            @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.ok(category);
    }
}