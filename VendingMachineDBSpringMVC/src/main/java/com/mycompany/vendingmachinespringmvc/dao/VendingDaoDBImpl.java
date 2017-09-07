/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachinespringmvc.dao;

import com.mycompany.vendingmachinespringmvc.model.Item;
import com.mycompany.vendingmachinespringmvc.service.NoItemInventoryException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hibernate.validator.internal.util.CollectionHelper.newHashMap;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author n0252282
 */
public class VendingDaoDBImpl implements VendingDao {

    private Map<Integer, Item> items = newHashMap();

    private static final String SQL_INSERT_ITEM
            = "insert into item"
            + "(itemName, price, quantity) "
            + "values (?, ?, ?)";
    private static final String SQL_DELETE_ITEM
            = "delete from item where itemId = ?";
    private static final String SQL_SELECT_ITEM
            = "select * from item where itemId = ?";
    private static final String SQL_UPDATE_ITEM
            = "update item set "
            + "itemName = ?, price = ?, quantity = ? "
            + "where itemId = ?";
    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from item";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public Item addItem(Item dvd) {
//        jdbcTemplate.update(SQL_INSERT_ITEM,
//                dvd.getItemTitle(),
//                dvd.getReleaseYear(),
//                dvd.getDirector(),
//                dvd.getRating(),
//                dvd.getNotes());
//
//        // query the database for the id that was just assigned to the new
//        // row in the database
//        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
//                Integer.class);
//        // set the new id value on the contact object and return it
//        dvd.setItemID(newId);
//        return dvd;
//    }

//    @Override
//    public void removeItem(Integer itemId) {
//        jdbcTemplate.update(SQL_DELETE_ITEM, itemId);
//    }

//    @Override
//    public void updateItem(Item dvd) {
//        jdbcTemplate.update(SQL_UPDATE_ITEM,
//                dvd.getItemTitle(),
//                dvd.getReleaseYear(),
//                dvd.getDirector(),
//                dvd.getRating(),
//                dvd.getNotes(),
//                dvd.getItemID());
//    }

    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new ItemMapper());
    }


    @Override
    public Item getItem(Integer itemId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new ItemMapper(), itemId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }


    private static final class ItemMapper implements RowMapper<Item> {

        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setItemId(rs.getInt("itemId"));
            item.setItemName(rs.getString("itemName"));
            item.setPrice(rs.getBigDecimal("price"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        }
    }

    @Override
    public Item makeSaleReduceInventory(Integer itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item removedItem = getItem(itemId);
        if (removedItem.getQuantity() > 0) {
            removedItem.setQuantity(removedItem.getQuantity() - 1);
            updateItem(removedItem);
        } else {
            throw new NoItemInventoryException("Not possible to reduce inventory below 0");
        }
        return removedItem;
    }
    
    
    
    private void updateItem(Item item) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getItemName(),
                item.getPrice(),
                item.getQuantity(),
                item.getItemId());
    }

}
