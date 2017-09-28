/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @category n0252282
 */
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from categories";

    private static final String SQL_SELECT_ALL_CATEGORIES_BY_NAME
            = "select * from categories c "
            + "where c.category_name like ?";

    private static final String SQL_SELECT_CATEGORY
            = "select * from categories where category_id = ?";

    private static final String SQL_ADD_CATEGORY
            = "insert into categories (category_name, category_description)"
            + " values (?, ?)";

    private static final String SQL_DELETE_CATEGORY
            = "delete from categories where category_id = ?";

    private static final String SQL_UPDATE_CATEGORY
            = "update categories set category_name = ?, category_description = ? "
            + "where category_id = ?";

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES,
                new CategoryDaoImpl.CategoryMapper());
    }

    @Override
    public List<Category> getAllCategoriesByName(String catName) {
        String categoryName = "%" + catName + "%";
        List<Category> categoryList = jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES_BY_NAME,
                new CategoryDaoImpl.CategoryMapper(), categoryName);
        return categoryList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCategory(Category category) {

        jdbcTemplate.update(SQL_ADD_CATEGORY,
                category.getName(),
                category.getDescription());

        int categoryID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class
        );

        category.setCategoryID(categoryID);
    }

    @Override
    public void deleteCategory(int categoryID) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryID);
    }

    @Override
    public void updateCategory(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY,
                category.getName(),
                category.getDescription(),
                category.getCategoryID());
    }

    @Override
    public Category getCategoryById(int categoryID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY,
                    new CategoryDaoImpl.CategoryMapper(), categoryID);
        } catch (EmptyResultDataAccessException ex) {

            return null;
        }
    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category category = new Category();
            category.setCategoryID(rs.getInt("category_id"));
            category.setName(rs.getString("category_name"));
            category.setDescription(rs.getString("category_description"));
            return category;
        }
    }

}
