/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Category;
import java.util.List;

/**
 *
 * @category n0252282
 */
public interface CategoryDao {

    public void addCategory (Category category);

    public void deleteCategory(int categoryID);

    public void updateCategory(Category category);

    public Category getCategoryById(int categoryID);

    public List<Category> getAllCategories();
    
    public List<Category> getAllCategoriesByName(String catName);
    
}
