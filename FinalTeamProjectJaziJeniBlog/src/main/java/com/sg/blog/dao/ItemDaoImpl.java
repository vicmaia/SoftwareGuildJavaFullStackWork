/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.dao;

import com.sg.blog.model.Item;
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
 * @item n0252282
 */
public class ItemDaoImpl implements ItemDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from items";

    private static final String SQL_SELECT_ITEM
            = "select * from items where item_id = ?";

    private static final String SQL_ADD_ITEM
            = "insert into items (item_name, item_description, item_price, item_quantity"
            + ") values (?, ?, ?, ?)";

    private static final String SQL_DELETE_ITEM
            = "delete from items where item_id = ?";

    private static final String SQL_UPDATE_ITEM
            = "update items set item_name = ?, item_description = ?, item_price = ? "
            + ", item_quantity = ? where item_id = ?";

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new ItemDaoImpl.ItemMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addItem(Item item) {

        jdbcTemplate.update(SQL_ADD_ITEM,
                item.getItemName(),
                item.getItemDescription(),
                item.getItemPrice(),
                item.getItemQuantity());

        int itemID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class
        );

        item.setItemID(itemID);
    }

    @Override
    public void deleteItem(int itemID) {
        jdbcTemplate.update(SQL_DELETE_ITEM, itemID);
    }

    @Override
    public void updateItem(Item item) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getItemName(),
                item.getItemDescription(),
                item.getItemPrice(),
                item.getItemQuantity(),
                item.getItemID());
    }

    @Override
    public Item getItemById(int itemID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ItemDaoImpl.ItemMapper(), itemID);
        } catch (EmptyResultDataAccessException ex) {

            return null;
        }
    }

    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setItemID(rs.getInt("item_id"));
            item.setItemName(rs.getString("item_name"));
            item.setItemDescription(rs.getString("item_description"));
            item.setItemPrice(rs.getBigDecimal("item_price"));
            item.setItemQuantity(rs.getInt("item_quantity"));
            return item;
        }
    }

}
