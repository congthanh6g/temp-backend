package com.projectsem4.backend.service;

import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Movie;

import java.util.List;

public interface CategoryService {
    boolean checkExistNameUpdate(String name, Category category);

    List<Category> getAllCategories();
    List<Category> getAllCategoriesByDeleteState(boolean isDeleted);

    Category saveCategory(Category category);
    Category getCategoryById(int id);
    void deleteCategoryById(int id);
}
