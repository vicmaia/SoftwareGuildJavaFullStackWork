/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Category;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author n0001123
 */
public class CategoryDaoStubImpl implements CategoryDao {

    Map<Integer, Category> categoryList = new HashMap();


    @Override
    public void addCategory(Category category) {
        categoryList.put(category.getCategoryID(), category);
    }

    @Override
    public void deleteCategory(int categoryID) {
        Category category = getCategoryById(categoryID);
        categoryList.remove(category.getCategoryID());
    }

    @Override
    public void updateCategory(Category category) {
        deleteCategory(category.getCategoryID());
        addCategory(category);
    }

    @Override
    public Category getCategoryById(int categoryID) {
        return categoryList.get(categoryID);
    }

    @Override
    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryList.values());
    }

    @Override
    public List<Category> getAllCategoriesByName(String catName) {
        return new ArrayList<>(categoryList.values());
    }

}
