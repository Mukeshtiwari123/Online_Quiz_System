package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Categories;

public interface CategoriesDao {
	Categories createCategory(Categories category);	
    List<Categories> getAllCategories();
    Categories getCategoryById(int categoryId);
    Categories updateCategory(int categoryId, Categories updatedCategory);
    String deleteCategory(int categoryId);
    boolean isCategoryNameExists(String name);
}
