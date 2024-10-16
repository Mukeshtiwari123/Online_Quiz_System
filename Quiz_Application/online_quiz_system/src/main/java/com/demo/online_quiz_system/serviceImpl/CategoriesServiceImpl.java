package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.CategoriesDao; // Assuming you have CategoriesDao
import com.demo.online_quiz_system.DaoImpl.CategoriesDaoImpl; // Assuming you have CategoriesDaoImpl
import com.demo.online_quiz_system.entities.Categories;
import com.demo.online_quiz_system.service.CategoriesService;

public class CategoriesServiceImpl implements CategoriesService {

    // Dependency on CategoriesDao
    CategoriesDao categoriesDao = new CategoriesDaoImpl();

    @Override
    public Categories createCategory(Categories category) {
        // Invoke CategoriesDaoImpl method to save category object
        return categoriesDao.createCategory(category);
    }

    @Override
    public List<Categories> getAllCategories() {
        // Invoke CategoriesDaoImpl method to retrieve all categories
        return categoriesDao.getAllCategories();
    }

    @Override
    public Categories getCategoryById(int categoryId) {
        // Invoke CategoriesDaoImpl method to retrieve a specific category by categoryId
        return categoriesDao.getCategoryById(categoryId);
    }

    @Override
    public Categories updateCategory(int categoryId, Categories updatedCategory) {
        // Invoke CategoriesDaoImpl method to update the category's details
        return categoriesDao.updateCategory(categoryId, updatedCategory);
    }

    @Override
    public String deleteCategory(int categoryId) {
        // Invoke CategoriesDaoImpl method to delete a category by categoryId
        return categoriesDao.deleteCategory(categoryId);
    }
    @Override
    public boolean isCategoryNameExists(String name) {
        return categoriesDao.isCategoryNameExists(name); // Delegate to the DAO
    }


}
