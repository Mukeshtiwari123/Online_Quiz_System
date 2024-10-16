package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Categories;
import java.util.List;

public interface CategoriesService {
    Categories createCategory(Categories category);
    List<Categories> getAllCategories();
    Categories getCategoryById(int categoryId);
    Categories updateCategory(int categoryId, Categories updatedCategory);
    String deleteCategory(int categoryId);
	boolean isCategoryNameExists(String name);
}
